package org.babor.boot_project_management.dto.sprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SprintResponseDto {
    private int id;
    private String name;
    private String goal;
    private int duration;
    private Boolean isActive;
}
