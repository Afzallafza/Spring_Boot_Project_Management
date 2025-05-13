package org.babor.boot_project_management.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private Boolean isActive;
    @OneToMany(mappedBy = "project")
    private List<Sprint> sprints;
    @OneToMany(mappedBy = "project")
    private List<Issue> issues;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.isActive = true;
        this.creationDate = LocalDateTime.now();
    }
}
