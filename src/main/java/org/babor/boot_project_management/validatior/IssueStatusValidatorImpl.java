package org.babor.boot_project_management.validatior;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.babor.boot_project_management.enums.IssueStatus;

import java.util.Arrays;

public class IssueStatusValidatorImpl implements ConstraintValidator<IssueStatusValidator, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (s != null) {
            try {
                IssueStatus.valueOf(s);
            } catch (IllegalArgumentException e) {
                isValid = false;
                constraintValidatorContext.buildConstraintViolationWithTemplate("""
                                Status must be one of these values""" + Arrays.toString(IssueStatus.values()))
                        .addConstraintViolation();
            }
        }
        return isValid;
    }


}
