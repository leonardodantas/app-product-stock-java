package com.product.stock.infra.http.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DetailsValidatorAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DetailsValid {
    public String message() default "Tamanho da lista não pode ser nula/vazia e items não podem ser repetir";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
