package org.babor.boot_project_management.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.babor.boot_project_management.enums.*;

@Data
@NoArgsConstructor
@Entity
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Description can not be empty")
    private String description;
    @Enumerated(EnumType.STRING)
    private IssueStatus status;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Priority must have a value")
    private IssuePriority priority;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User reporter;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User assignee;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Issue issue;

    public Subtask(String name, String description, IssuePriority priority, User assignee, User reporter, Issue issue) {
        this.name = name;
        this.description = description;
        this.status = IssueStatus.PENDING;
        this.priority = priority;
        this.reporter = assignee;
        this.assignee = reporter;
        this.issue = issue;
    }
}
