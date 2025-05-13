package org.babor.boot_project_management.dto.issue;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueStatus;
import org.babor.boot_project_management.enums.IssueType;

import java.util.Set;

@Data
@AllArgsConstructor
public class IssueFilterRequestDto {
    private Set<IssueType> types;
    private Set<IssuePriority> priorities;
    private Set<IssueStatus> statuses;
}
