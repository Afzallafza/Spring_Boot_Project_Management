package org.babor.boot_project_management.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMembershipDto {
    @NotNull(message = "Id can not be null")
    private int id;
    @NotBlank(message = "Role can not be empty")
    @NotNull(message = "Role can not be null")
    private String role;

}
