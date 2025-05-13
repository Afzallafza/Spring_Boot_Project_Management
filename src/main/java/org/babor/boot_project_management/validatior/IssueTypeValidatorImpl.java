package org.babor.boot_project_management.validatior;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.babor.boot_project_management.enums.IssueType;

import java.util.Arrays;

public class IssueTypeValidatorImpl implements ConstraintValidator<IssueTypeValidator, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        constraintValidatorContext.disableDefaultConstraintViolation();
        try {
            IssueType.valueOf(s);
        } catch (IllegalArgumentException e) {
            isValid = false;
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Type must be one of these values" + Arrays.toString(IssueType.values())
            ).addConstraintViolation();
        }
        return isValid;
    }
}
