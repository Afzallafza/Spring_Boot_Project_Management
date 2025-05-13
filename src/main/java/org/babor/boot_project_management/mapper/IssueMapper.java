package org.babor.boot_project_management.mapper;

import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.issue.*;
import org.babor.boot_project_management.entity.Issue;
import org.babor.boot_project_management.entity.Project;
import org.babor.boot_project_management.entity.Sprint;
import org.babor.boot_project_management.entity.User;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueStatus;
import org.babor.boot_project_management.enums.IssueType;
import org.babor.boot_project_management.repository.ProjectRepository;
import org.babor.boot_project_management.repository.UserRepository;
import org.babor.boot_project_management.service.IssueService;
import org.babor.boot_project_management.service.SprintService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IssueMapper {
    private final ProjectRepository projectRepository;
    private final SprintService sprintService;
    private final UserRepository userRepository;

    @Transactional
    public Issue mapToEntity(IssueRequestDto issueRequestDto, int projectId) {
        Project project = projectRepository.findByIdCustom(projectId);
        Sprint sprint = sprintService.findByIdCustom(issueRequestDto.getSprintId());
        User user = userRepository.findByIdCustom(issueRequestDto.getAssigneeId());
        return new Issue(issueRequestDto.getName(), issueRequestDto.getDescription(),
                IssueType.valueOf(issueRequestDto.getIssueType()), IssuePriority.valueOf(issueRequestDto.getPriority()),
                sprint, user, project);
    }

    public IssueResponseDto mapToDto(Issue issue) {
        return new IssueResponseDto(
                issue.getId(),
                issue.getName(),
                issue.getType(),
                issue.getPriority(),
                issue.getStatus(),
                issue.getAssignee().getId(),
                issue.getSprint() == null ? 0 : issue.getSprint().getId(),
                issue.getProject().getId()
        );
    }

    public void mapUpdates(Issue issue, IssueUpdateRequestDto issueUpdateRequestDto) {
        if (issueUpdateRequestDto.getName() != null) {
            issue.setName(issueUpdateRequestDto.getName());
        }
        if (issueUpdateRequestDto.getDescription() != null) {
            issue.setDescription(issueUpdateRequestDto.getDescription());
        }
        if (issueUpdateRequestDto.getPriority() != null) {
            issue.setPriority(IssuePriority.valueOf(issueUpdateRequestDto.getPriority()));
        }
        if (issueUpdateRequestDto.getStatus() != null) {
            issue.setStatus(IssueStatus.valueOf(issueUpdateRequestDto.getStatus()));
        }
    }

    public IssueFilterRequestDto mapFilters(IssueFilterRequestDto filterRequestDto,
                                            Set<Integer> sprints, int projectId) {
        if (filterRequestDto.getPriorities() == null) {
            filterRequestDto.setPriorities(Set.of(IssuePriority.values()));
        }
        if (filterRequestDto.getTypes() == null) {
            filterRequestDto.setTypes(Set.of(IssueType.values()));
        }
        if (filterRequestDto.getStatuses() == null) {
            filterRequestDto.setStatuses(Set.of(IssueStatus.values()));
        }

        return filterRequestDto;

    }
}
