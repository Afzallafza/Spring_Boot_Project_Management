package org.babor.boot_project_management.dto.subtask;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueStatus;
import org.babor.boot_project_management.validatior.IssuePriorityValidator;
import org.babor.boot_project_management.validatior.IssueStatusValidator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubtaskUpdateRequestDto {

    private String name;
    private String description;
    @IssuePriorityValidator(enumClass = IssuePriority.class)
    private String priority;
    @IssueStatusValidator(enumClass = IssueStatus.class)
    private String status;
    private Integer assigneeId;
}
