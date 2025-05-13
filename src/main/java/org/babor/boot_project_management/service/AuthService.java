package org.babor.boot_project_management.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.babor.boot_project_management.dto.issue.IssueResponseDto;
import org.babor.boot_project_management.dto.subtask.SubtaskResponseDto;
import org.babor.boot_project_management.dto.user.UserResponseDto;
import org.babor.boot_project_management.entity.Issue;
import org.babor.boot_project_management.entity.Subtask;
import org.babor.boot_project_management.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final SubtaskService subtaskService;
    private final IssueService issueService;

    public void assertAdmin() {
        User user = userService.findByUsernameCustom(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRole() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    public void assertTeamLead(int projectId) {
        User user = userService.findByUsernameCustom(SecurityContextHolder.getContext().getAuthentication().getName());
        boolean canModifyIssue = false;
        for (UserResponseDto userResponseDto : userService.findAllByProjectId(projectId)) {
            if (userResponseDto.getId() == user.getId() && userResponseDto.getRole().equals("Team_Lead")) {
                canModifyIssue = true;
                break;
            }
        }
        if (!canModifyIssue) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    public void assertIssueAssignee(int issueId) {
        User user = userService.findByUsernameCustom(SecurityContextHolder.getContext().getAuthentication().getName());
        Issue issue = issueService.findByIdCustom(issueId);
        if (!(issue.getAssignee().getId() == user.getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    public void assertSubtaskAssignee(int subtaskId) {
        User user = userService.findByUsernameCustom(SecurityContextHolder.getContext().getAuthentication().getName());
        Subtask subtask = subtaskService.findByIdCustom(subtaskId);
        if (!(subtask.getAssignee().getId() == user.getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

}
