package org.babor.boot_project_management.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.issue.IssueResponseDto;
import org.babor.boot_project_management.dto.sprint.SprintRequestDto;
import org.babor.boot_project_management.dto.sprint.SprintResponseDto;
import org.babor.boot_project_management.dto.sprint.SprintUpdateRequestDto;
import org.babor.boot_project_management.exception.SprintNotFoundException;
import org.babor.boot_project_management.service.IssueService;
import org.babor.boot_project_management.service.SprintService;
import org.babor.boot_project_management.validatior.ProjectValidator;
import org.babor.boot_project_management.validatior.SprintValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SprintController {
    private final SprintService sprintService;
    private final SprintValidator sprintValidator;
    private final IssueService issueService;
    private final ProjectValidator projectValidator;

    @PreAuthorize("@authService.assertTeamLead(#projectId)")
    @PostMapping("/projects/{id}/sprints")
    @Operation(
            summary = "Create Sprint"
    )
    public ResponseEntity<?> create(@PathVariable("id") int projectId,
                                    @Valid @RequestBody SprintRequestDto sprintRequestDto) {
        projectValidator.isPresent(projectId);
        return new ResponseEntity<>(sprintService.save(sprintRequestDto, projectId), HttpStatus.CREATED);
    }

    @GetMapping("/sprints/{id}")
    @Operation(
            summary = "Get Sprint By Id"
    )
    public ResponseEntity<SprintResponseDto> get(@PathVariable("id") int sprintId) throws SprintNotFoundException {
        sprintValidator.isPresent(sprintId);
        return new ResponseEntity<>(sprintService.findDtoById(sprintId), HttpStatus.OK);
    }

    @PreAuthorize("@authService.assertTeamLead(#projectId)")
    @PatchMapping("/projects/{id}/sprints/{id2}")
    @Operation(
            summary = "Update Sprint"
    )
    public ResponseEntity<?> update(
            @PathVariable("id") int projectId,
            @PathVariable("id2") int sprintId,
            @Valid @RequestBody SprintUpdateRequestDto sprintUpdateRequestDto) {
        projectValidator.isPresent(projectId);
        return new ResponseEntity<>(sprintService.update(sprintUpdateRequestDto, sprintId), HttpStatus.OK);
    }

    @GetMapping("/sprints/{id}/issues")
    @Operation(
            summary = "Get All Issues By Sprint"
    )
    public ResponseEntity<List<IssueResponseDto>> getIssues(@PathVariable("id") int sprintId) {
        sprintValidator.isPresent(sprintId);
        return new ResponseEntity<>(issueService.findAllBySprintId(sprintId), HttpStatus.OK);

    }
}

