package org.babor.boot_project_management.validatior;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IssueTypeValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IssueTypeValidator {
    String message() default "Invalid value";

    Class<?>[] groups() default {};

    Class<? extends Enum<?>> enumClass();

    Class<? extends Payload>[] payload() default {};

}
