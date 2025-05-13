package org.babor.boot_project_management.service;

import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.sprint.SprintRequestDto;
import org.babor.boot_project_management.dto.sprint.SprintResponseDto;
import org.babor.boot_project_management.dto.sprint.SprintUpdateRequestDto;
import org.babor.boot_project_management.entity.Project;
import org.babor.boot_project_management.entity.Sprint;
import org.babor.boot_project_management.mapper.SprintMapper;
import org.babor.boot_project_management.repository.SprintRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;
    private final ProjectService projectService;
    private final ModelMapper modelMapper;
    private final SprintMapper sprintMapper;
    @Transactional
    public SprintResponseDto save(SprintRequestDto sprintRequestDto,int projectId) {
        Project project = projectService.findByIdCustom(projectId);
        Sprint sprint = new Sprint(project, sprintRequestDto.getGoal(), sprintRequestDto.getName(), sprintRequestDto.getDuration());
        sprintRepository.save(sprint);
        return modelMapper.map(sprint, SprintResponseDto.class);
    }

    @Transactional
    public Sprint findByIdCustom(int sprintId) {
        return sprintRepository.findByIdCustom(sprintId);
    }

    @Transactional
    public SprintResponseDto findDtoById(int sprintId) {
        return modelMapper.map(sprintRepository.findByIdCustom(sprintId), SprintResponseDto.class);
    }

    @Transactional
    public List<SprintResponseDto> findAllByProjectId(int projectId) {
        Project project = projectService.findByIdCustom(projectId);
        return sprintRepository.findAllByProject(project);
    }

    @Transactional
    public SprintResponseDto update(SprintUpdateRequestDto sprintUpdateRequestDto, int sprintId) {
        Sprint sprint = sprintRepository.findByIdCustom(sprintId);
        sprintMapper.mapUpdates(sprint, sprintUpdateRequestDto);
        return modelMapper.map(sprint, SprintResponseDto.class);
    }

}
