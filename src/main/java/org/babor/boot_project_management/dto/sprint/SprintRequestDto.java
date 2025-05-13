package org.babor.boot_project_management.dto.sprint;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SprintRequestDto {
    @NotBlank(message = "Goal can not be empty")
    @NotNull(message = "Goal can not be null")
    private String goal;
    @NotBlank(message = "Name can not be empty")
    @NotNull(message = "Name can not be null")
    private String name;
    @NotNull(message = "Duration can not be null")
    private int duration;
}
