package org.babor.boot_project_management.service;


import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.issue.*;
import org.babor.boot_project_management.entity.Issue;
import org.babor.boot_project_management.mapper.IssueMapper;
import org.babor.boot_project_management.repository.IssueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final SprintService sprintService;
    private final IssueMapper issueMapper;


    @Transactional
    public IssueResponseDto save(IssueRequestDto issueRequestDto, int projectId) {
        Issue issue = issueMapper.mapToEntity(issueRequestDto, projectId);
        issueRepository.save(issue);
        return modelMapper.map(issue, IssueResponseDto.class);
    }

    @Transactional
    public Issue findByIdCustom(int issueId) {
        return issueRepository.findByIdCustom(issueId);
    }

    @Transactional
    public IssueResponseDto findDtoById(int issueId) {
        return issueMapper.mapToDto(issueRepository.findByIdCustom(issueId));
    }

    @Transactional
    public IssueUpdateResponseDto update(int issueId, IssueUpdateRequestDto issueUpdateRequestDto) {
        Issue issue = issueRepository.findByIdCustom(issueId);
        issueMapper.mapUpdates(issue, issueUpdateRequestDto);
        return modelMapper.map(issue, IssueUpdateResponseDto.class);
    }

    @Transactional
    public List<IssueResponseDto> filter(IssueFilterRequestDto filterRequestDto,
                                         Set<Integer> sprints, int projectId) {
        filterRequestDto = issueMapper.mapFilters(filterRequestDto, sprints, projectId);
        if (sprints == null) {
            sprints = sprintService.findAllByProjectId(projectId).stream()
                    .map(s -> s.getId()).collect(Collectors.toSet());
        }
        return issueRepository.filter(filterRequestDto.getTypes(), filterRequestDto.getPriorities(), filterRequestDto.getStatuses(), sprints);
    }

    @Transactional
    public List<IssueResponseDto> findAllBySprintId(int sprintId) {
        return issueRepository.findAllBySprintId(sprintId);
    }

}
