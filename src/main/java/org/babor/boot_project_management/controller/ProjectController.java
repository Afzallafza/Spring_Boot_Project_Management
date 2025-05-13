package org.babor.boot_project_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.issue.IssueFilterRequestDto;
import org.babor.boot_project_management.dto.issue.IssueRequestDto;
import org.babor.boot_project_management.dto.project.ProjectMembershipDto;
import org.babor.boot_project_management.dto.project.ProjectRequestDto;
import org.babor.boot_project_management.dto.project.ProjectResponseDto;
import org.babor.boot_project_management.dto.sprint.SprintResponseDto;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueStatus;
import org.babor.boot_project_management.enums.IssueType;
import org.babor.boot_project_management.exception.ProjectNotFoundException;
import org.babor.boot_project_management.service.*;
import org.babor.boot_project_management.validatior.ProjectValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectValidator projectValidator;
    private final SprintService sprintService;
    private final IssueService issueService;
    private final UserService userService;

    @PreAuthorize("@authService.assertAdmin()")
    @PostMapping
    @Operation(
            summary = "Create Project"
    )
    public ResponseEntity<ProjectResponseDto> create(@Valid @RequestBody ProjectRequestDto project) {
        return new ResponseEntity<>(projectService.save(project), HttpStatus.CREATED);
    }

    @PreAuthorize("@authService.assertAdmin()")
    @PostMapping("/{id}/users")
    @Operation(
            summary = "Add Member to Project"
    )
    public ResponseEntity<?> add(@PathVariable("id") int projectId, @Valid @RequestBody List<ProjectMembershipDto> members)
            throws ProjectNotFoundException {
        projectValidator.isPresent(projectId);
        return new ResponseEntity<>(projectService.include(projectId, members), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Get All Projects"
    )
    public ResponseEntity<List<ProjectResponseDto>> getAll() {
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/sprints")
    @Operation(
            summary = "Get All Sprints by Project"
    )
    public ResponseEntity<List<SprintResponseDto>> getAllSprints(@PathVariable("id") int projectId) {
        projectValidator.isPresent(projectId);
        return new ResponseEntity<>(sprintService.findAllByProjectId(projectId), HttpStatus.OK);
    }

    @GetMapping("/{id}/issues")
    @Operation(
            summary = "Filter Issue By Type,Priority,Status & Sprint"
    )
    public ResponseEntity<?> filter(@RequestParam(value = "type", required = false) Set<IssueType> types,
                                    @RequestParam(value = "priority", required = false) Set<IssuePriority> priorities,
                                    @RequestParam(value = "status", required = false) Set<IssueStatus> statuses,
                                    @RequestParam(value = "sprint", required = false) Set<Integer> sprints,
                                    @PathVariable("id") int projectId
    ) {
        projectValidator.isPresent(projectId);
        IssueFilterRequestDto filterRequestDto = new IssueFilterRequestDto(types, priorities, statuses);
        return new ResponseEntity<>(issueService.filter(filterRequestDto, sprints, projectId), HttpStatus.OK);
    }

    @GetMapping("/{id}/users")
    @Operation(
            summary = "Get All Users By Project"
    )
    public ResponseEntity<?> getUsers(@PathVariable("id") int projectId) {
        projectValidator.isPresent(projectId);
        return new ResponseEntity<>(userService.findAllByProjectId(projectId), HttpStatus.OK);
    }

    @PreAuthorize("@authService.assertTeamLead(#projectId)")
    @PostMapping("/{id}/issues")
    @Operation(
            summary = "Create Issue"
    )
    public ResponseEntity<?> create(@Valid @RequestBody IssueRequestDto issue, @PathVariable("id") int projectId) {
        return new ResponseEntity<>(issueService.save(issue, projectId), HttpStatus.CREATED);
    }
}
