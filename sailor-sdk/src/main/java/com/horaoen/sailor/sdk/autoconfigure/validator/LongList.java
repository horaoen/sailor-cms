package com.horaoen.sailor.sdk.autoconfigure.validator;

import javax.validation.Payload;

/**
 * @author horaoen
 */
public @interface LongList {
    String message() default "Integer list cannot can't be blank";

    long min() default 0L;

    long max() default Long.MAX_VALUE;

    boolean allowBlank() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
