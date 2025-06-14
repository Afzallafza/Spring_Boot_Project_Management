package org.babor.boot_project_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Project project;
    @NotNull(message = "Goal must have a value")
    private String goal;
    @NotBlank(message = "Name can no be empty")
    private String name;
    private LocalDateTime startDate;
    @NotNull(message = "Duration must be a positive integer")
    private int duration;
    private LocalDateTime endDate;
    private Boolean isActive;
    @OneToMany(mappedBy = "sprint")
    private List<Issue> issues;

    public Sprint(Project project, String goal, String name, int duration) {
        this.project = project;
        this.goal = goal;
        this.name = name;
        this.startDate = LocalDateTime.now();
        this.duration = duration;
        isActive = true;
    }

}
