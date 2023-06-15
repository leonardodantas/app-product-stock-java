package com.product.stock.infra.http.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Component
public class DetailsValidatorAnnotation implements ConstraintValidator<DetailsValid, List<String>> {

    @Override
    public boolean isValid(final List<String> details, final ConstraintValidatorContext constraintValidatorContext) {
        if (isEmpty(details)) {
            return false;
        }

        final var detailsSet = details.stream()
                .filter(detail -> detail.trim().length() > 1)
                .map(String::toUpperCase)
                .collect(Collectors.toSet());

        return detailsSet.size() == details.size();
    }
}
