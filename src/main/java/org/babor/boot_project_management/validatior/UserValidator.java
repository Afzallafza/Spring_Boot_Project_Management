package org.babor.boot_project_management.validatior;

import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.Constants;
import org.babor.boot_project_management.dto.user.UserRequestDto;
import org.babor.boot_project_management.service.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserService userService;

    public void userCreationValidator(UserRequestDto user) {
        StringBuilder errors = new StringBuilder();
        if (userService.findByUsernameCustom(user.getUsername()) != null) {
            errors.append("User already exists!\n");
        }
        if (!user.getEmail().matches(Constants.emailRegex)) {
            errors.append("The email address is invalid!\n");
        }
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
    }
}
