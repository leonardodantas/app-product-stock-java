package com.product.stock.infra.http.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^[A-Z]{3}\\d{3}[A-Z]{3}$")
public @interface CodeValid {
    String message() default "O código deve seguir o seguinte padrão AAA000AAA";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
