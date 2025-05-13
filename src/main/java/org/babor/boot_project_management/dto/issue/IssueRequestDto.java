package org.babor.boot_project_management.dto.issue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueType;
import org.babor.boot_project_management.validatior.IssuePriorityValidator;
import org.babor.boot_project_management.validatior.IssueTypeValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueRequestDto {
    @NotBlank(message = "Name can not be empty")
    @NotNull(message = "Name can not be null")
    private String name;
    @NotBlank(message = "Description can not be empty")
    @NotNull(message = "Description can not be null")
    private String description;
    @NotNull(message = "Type can not be empty")
    @IssueTypeValidator(enumClass = IssueType.class)
    private String issueType;
    @NotNull(message = "AssigneeId can not be null")
    private int assigneeId;
    @NotNull(message = "Priority can not be null")
    @IssuePriorityValidator(enumClass = IssuePriority.class)
    private String priority;
    private int sprintId;
}
