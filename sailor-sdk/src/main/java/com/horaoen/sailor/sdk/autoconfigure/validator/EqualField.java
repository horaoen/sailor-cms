package com.horaoen.sailor.sdk.autoconfigure.validator;

import com.horaoen.sailor.sdk.autoconfigure.validator.impl.EqualFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author horaoen
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {EqualFieldValidator.class}
)
public @interface EqualField {
    String srcField() default "";

    String dstField() default "";

    String message() default "the two fields must be equal";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
