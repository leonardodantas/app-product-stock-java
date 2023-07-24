package com.product.stock.infra.http.jsons.response;

import com.product.stock.domain.Review;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewResponse(
        LocalDateTime date,
        String description
) {
    public static ReviewResponse from(final Review review) {
        return new ReviewResponse(review.date(), review.description());
    }
}
