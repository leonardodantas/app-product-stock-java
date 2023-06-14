package com.product.stock.infra.http.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class DetailsValidatorAnnotation implements ConstraintValidator<DetailsValid, List<String>> {

    //TODO
    //REFATORAR
    @Override
    public boolean isValid(final List<String> details, final ConstraintValidatorContext constraintValidatorContext) {
        if (isNull(details) || details.isEmpty()) {
            return false;
        }

        final var detailsSet = details.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());

        return detailsSet.size() == details.size();
    }
}
