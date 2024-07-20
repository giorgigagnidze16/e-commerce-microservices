package com.ecom.microservice.web.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ecom.microservice.web.validation.PriceRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


/**
 * Validates if a maximum price is greater or equal than minimum.
 */
@Documented
@Constraint(validatedBy = PriceRangeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface ValidPriceRange {
    String message() default "Invalid price range of ${validatedValue}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
