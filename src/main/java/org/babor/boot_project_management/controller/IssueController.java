package org.babor.boot_project_management.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.issue.IssueRequestDto;
import org.babor.boot_project_management.dto.issue.IssueUpdateRequestDto;
import org.babor.boot_project_management.service.AuthService;
import org.babor.boot_project_management.service.IssueService;
import org.babor.boot_project_management.validatior.IssueValidator;
import org.babor.boot_project_management.validatior.ProjectValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class IssueController {
    private final IssueService issueService;
    private final IssueValidator issueValidator;
    private final ProjectValidator projectValidator;

    @GetMapping("/issues/{id}")
    @Operation(
            summary = "Get Issue By Id"
    )
    public ResponseEntity<?> findByIdCustom(@PathVariable("id") int issueId) {
        issueValidator.isPresent(issueId);
        return new ResponseEntity<>(issueService.findDtoById(issueId), HttpStatus.OK);
    }

    @PreAuthorize("@authService.assertIssueAssignee(#issueId)")
    @PatchMapping("/projects/{id}/issues/{id2}")
    @Operation(
            summary = "Update Issue"
    )
    public ResponseEntity<?> update(@PathVariable("id") int projectId,
                                    @PathVariable("id2") int issueId,
                                    @Valid @RequestBody IssueUpdateRequestDto issueUpdateRequestDto) {
        projectValidator.isPresent(projectId);
        issueValidator.isPresent(issueId);
        return new ResponseEntity<>(issueService.update(issueId, issueUpdateRequestDto), HttpStatus.OK);
    }

}