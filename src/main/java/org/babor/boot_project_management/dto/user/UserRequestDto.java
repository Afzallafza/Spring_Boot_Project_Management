package org.babor.boot_project_management.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "Name can not be empty")
    @NotNull(message = "Name can not be null")
    private String name;
    @NotBlank(message = "Name can not be empty")
    @NotNull(message = "Name can not be null")
    private String email;
    @NotBlank(message = "Name can not be empty")
    @NotNull(message = "Name can not be null")
    private String username;
    @NotBlank(message = "Name can not be empty")
    @NotNull(message = "Name can not be null")
    private String password;
}
