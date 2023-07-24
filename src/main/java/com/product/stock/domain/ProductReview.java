package com.product.stock.domain;

import java.util.List;

public record ProductReview(
        String id,
        String code,
        String name,
        String description,
        List<Review> review
) {
}
