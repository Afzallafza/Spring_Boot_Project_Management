package org.babor.boot_project_management.dto.subtask;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueStatus;
import org.babor.boot_project_management.validatior.IssuePriorityValidator;
import org.babor.boot_project_management.validatior.IssueStatusValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubtaskRequestDto {
    @NotNull(message = "Name must have a value")
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotNull(message = "Description must have a value")
    @NotBlank(message = "Description can not be empty")
    private String description;
    @NotNull(message = "Priority can not be null")
    @IssuePriorityValidator(enumClass = IssuePriority.class)
    private String priority;
    @NotNull(message = "ReporterId can not be null")
    private int reporterId;
    @NotNull(message = "AssigneeId can not be null")
    private int assigneeId;
}
