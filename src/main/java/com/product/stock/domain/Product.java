package com.product.stock.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Product(
        String id,
        String name,
        String description,
        BigDecimal price,
        int quantity,
        LocalDateTime create
) {

}
