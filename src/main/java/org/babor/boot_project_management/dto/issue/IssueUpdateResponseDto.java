package org.babor.boot_project_management.dto.issue;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueUpdateResponseDto {
    private int id;
    private String name;
    private String description;
    private IssueStatus status;
    private IssuePriority priority;

}
