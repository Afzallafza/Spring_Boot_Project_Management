package org.babor.boot_project_management.validatior;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.babor.boot_project_management.enums.IssuePriority;

import java.util.Arrays;

public class IssuePriorityValidatorImpl implements ConstraintValidator<IssuePriorityValidator, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (s != null) {
            try {
                IssuePriority.valueOf(s);
            } catch (IllegalArgumentException e) {
                isValid = false;
                constraintValidatorContext.buildConstraintViolationWithTemplate(
                        "Priority must be one of these values" + Arrays.toString(IssuePriority.values())
                ).addConstraintViolation();
            }
        }
        return isValid;
    }
}
