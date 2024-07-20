package com.ecom.microservice.web.validation;

import com.ecom.microservice.api.model.PriceRange;
import com.ecom.microservice.web.validation.annotation.ValidPriceRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validates if maximum price is greater or equal than minimum.
 */
public class PriceRangeValidator implements ConstraintValidator<ValidPriceRange, PriceRange> {

    @Override
    public boolean isValid(PriceRange value, ConstraintValidatorContext context) {
        if (value.min() != null && value.max() != null) {
            return value.max().compareTo(value.min()) >= 0;
        }
        return true;
    }
}
