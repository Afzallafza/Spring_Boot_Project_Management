package org.babor.boot_project_management.service;

import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.subtask.SubtaskRequestDto;
import org.babor.boot_project_management.dto.subtask.SubtaskResponseDto;
import org.babor.boot_project_management.dto.subtask.SubtaskUpdateRequestDto;
import org.babor.boot_project_management.dto.subtask.SubtaskUpdateResponseDto;
import org.babor.boot_project_management.entity.Issue;
import org.babor.boot_project_management.entity.Subtask;
import org.babor.boot_project_management.entity.User;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.mapper.SubtaskMapper;
import org.babor.boot_project_management.repository.IssueRepository;
import org.babor.boot_project_management.repository.SubtaskRepository;
import org.babor.boot_project_management.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubtaskService {
    private final SubtaskRepository subtaskRepository;
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final SubtaskMapper subtaskMapper;


    @Transactional
    public SubtaskResponseDto save(SubtaskRequestDto subtaskRequestDto, int issueId) {
        Issue issue = issueRepository.findByIdCustom(issueId);
        User reporter = userRepository.findByIdCustom(subtaskRequestDto.getReporterId());
        User assignee = userRepository.findByIdCustom(subtaskRequestDto.getAssigneeId());
        Subtask subtask = new Subtask(subtaskRequestDto.getName(), subtaskRequestDto.getDescription(),
                IssuePriority.valueOf(subtaskRequestDto.getPriority()), reporter, assignee, issue
        );
        subtaskRepository.save(subtask);
        return subtaskMapper.toDto(subtask);
    }

    @Transactional
    public SubtaskUpdateResponseDto update(SubtaskUpdateRequestDto subtaskRequestDto, int subtaskId) {
        Subtask subtask = subtaskRepository.findByIdCustom(subtaskId);
        subtaskMapper.mapUpdates(subtask, subtaskRequestDto);
        return modelMapper.map(subtaskMapper.toDto(subtask), SubtaskUpdateResponseDto.class);
    }

    @Transactional
    public List<SubtaskResponseDto> findAllByIssueId(int issueId) {
        return subtaskRepository.findAllByIssueId(issueId);
    }

    @Transactional
    public Subtask findByIdCustom(int subtaskId) {
        return subtaskRepository.findByIdCustom(subtaskId);
    }

    @Transactional
    public SubtaskResponseDto findDtoById(int subtaskId) {
        return modelMapper.map(subtaskRepository.findByIdCustom(subtaskId), SubtaskResponseDto.class);
    }
}
