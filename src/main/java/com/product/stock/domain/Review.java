package com.product.stock.domain;

import java.time.LocalDateTime;

public record Review(
        LocalDateTime date,
        String description
) {
}
