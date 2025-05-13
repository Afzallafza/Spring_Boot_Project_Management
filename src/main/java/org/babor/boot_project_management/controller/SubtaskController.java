package org.babor.boot_project_management.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.subtask.SubtaskRequestDto;
import org.babor.boot_project_management.dto.subtask.SubtaskUpdateRequestDto;
import org.babor.boot_project_management.service.SubtaskService;
import org.babor.boot_project_management.validatior.IssueValidator;
import org.babor.boot_project_management.validatior.SubtaskValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")

public class SubtaskController {
    private final SubtaskService subtaskService;
    private final IssueValidator issueValidator;
    private final SubtaskValidator subtaskValidator;

    @PreAuthorize("@authService.assertIssueAssignee(#issueId)")
    @PostMapping("/issues/{id}/subtasks")
    @Operation(
            summary = "Create Subtask"
    )
    public ResponseEntity<?> create(@PathVariable("id") Integer issueId, @RequestBody SubtaskRequestDto subtaskRequestDto) {
        issueValidator.isPresent(issueId);
        return new ResponseEntity<>(subtaskService.save(subtaskRequestDto, issueId), HttpStatus.CREATED);
    }

    @GetMapping("/subtasks/{id}")
    @Operation(
            summary = "Get Subtask By Id"
    )
    public ResponseEntity<?> get(@PathVariable("id") Integer subtaskId) {
        return new ResponseEntity<>(subtaskService.findDtoById(subtaskId), HttpStatus.OK);
    }

    @PreAuthorize("@authService.assertSubtaskAssignee(#subtaskId)")
    @PatchMapping("/subtasks/{id}")
    @Operation(
            summary = "Update Subtask"
    )
    public ResponseEntity<?> update(@PathVariable("id") Integer subtaskId,
                                    @Valid @RequestBody SubtaskUpdateRequestDto subtaskUpdateRequestDto) {
        subtaskValidator.isPresent(subtaskId);
        return new ResponseEntity<>(subtaskService.update(subtaskUpdateRequestDto, subtaskId), HttpStatus.OK);
    }

    @GetMapping("/issues/{id}/subtasks")
    @Operation(
            summary = "Get All Subtasks By Issue"
    )
    public ResponseEntity<?> getAll(@PathVariable("id") Integer issueId) {
        issueValidator.isPresent(issueId);
        return new ResponseEntity<>(subtaskService.findAllByIssueId(issueId), HttpStatus.OK);
    }

}
