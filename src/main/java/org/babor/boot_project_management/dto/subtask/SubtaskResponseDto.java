package org.babor.boot_project_management.dto.subtask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubtaskResponseDto {
    private Integer id;
    private String name;
    private String description;
    private IssuePriority priority;
    private IssueStatus status;
    private Integer assigneeId;
    private Integer reporterId;
}
