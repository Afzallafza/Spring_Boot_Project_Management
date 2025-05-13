package org.babor.boot_project_management.dto.issue;

import jakarta.validation.constraints.NotBlank;
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
public class IssueUpdateRequestDto {
    private String name;
    private String description;
    @IssueStatusValidator(enumClass = IssueStatus.class)
    private String status;
    @IssuePriorityValidator(enumClass = IssuePriority.class)
    private String priority;
}
