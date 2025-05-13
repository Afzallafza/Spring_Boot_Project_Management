package org.babor.boot_project_management.service;


import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.project.ProjectMembershipDto;
import org.babor.boot_project_management.dto.project.ProjectRequestDto;
import org.babor.boot_project_management.dto.project.ProjectResponseDto;
import org.babor.boot_project_management.dto.user.UserResponseDto;
import org.babor.boot_project_management.entity.Project;
import org.babor.boot_project_management.entity.User;
import org.babor.boot_project_management.entity.UserProject;
import org.babor.boot_project_management.repository.ProjectRepository;
import org.babor.boot_project_management.repository.UserProjectRepository;
import org.babor.boot_project_management.validatior.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserProjectRepository userProjectRepository;
    private final UserValidator userValidator;

    @Transactional
    public ProjectResponseDto save(ProjectRequestDto project) {
        return modelMapper.map(projectRepository
                .save(modelMapper.map(project, Project.class)), ProjectResponseDto.class);
    }

    @Transactional
    public Project findByIdCustom(int projectId) {
        return projectRepository.findByIdCustom(projectId);
    }

    @Transactional
    public ProjectResponseDto findDtoById(int projectId) {
        return modelMapper.map(projectRepository.findByIdCustom(projectId), ProjectResponseDto.class);
    }

    @Transactional
    public List<UserResponseDto> include(int projectId, List<ProjectMembershipDto> members) {
        Project project = projectRepository.findByIdCustom(projectId);
        List<UserResponseDto> inlcudedList = new ArrayList<>();
        for (ProjectMembershipDto member : members) {
            User user = userService.findByIdCustom(member.getId());
            UserProject userProject = new UserProject(project, user, member.getRole());
            userProjectRepository.save(userProject);
            inlcudedList.add(modelMapper.map(user, UserResponseDto.class));
        }
        return inlcudedList;
    }

    @Transactional
    public List<ProjectResponseDto> findAll() {
        return projectRepository.findAllCustom();
    }
}
