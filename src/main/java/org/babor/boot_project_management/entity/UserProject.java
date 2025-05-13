package org.babor.boot_project_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Project project;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User member;
    private String role;

    public UserProject(Project project, User user, String role) {
        this.project = project;
        this.member = user;
        this.role = role;
    }

}
