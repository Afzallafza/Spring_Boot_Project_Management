package org.babor.boot_project_management.dto.issue;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueStatus;
import org.babor.boot_project_management.enums.IssueType;

@Data
@NoArgsConstructor
public class IssueResponseDto {
    private int id;
    private String name;
    private IssueType issueType;
    private IssuePriority priority;
    private IssueStatus status;
    private int assigneeId;
    private int sprintId;
    private int projectId;

    public IssueResponseDto(int id, String name, IssueType type,
                            IssuePriority priority, IssueStatus status,
                            int assigneeId, int sprintId, int projectId) {
        this.id = id;
        this.name = name;
        this.issueType = type;
        this.priority = priority;
        this.status = status;
        this.assigneeId = assigneeId;
        this.sprintId = sprintId;
        this.projectId = projectId;
    }
}
