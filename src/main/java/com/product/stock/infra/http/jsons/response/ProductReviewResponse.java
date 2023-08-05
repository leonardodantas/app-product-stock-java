package com.product.stock.infra.http.jsons.response;

import com.product.stock.domain.ProductReview;

import java.util.List;

public record ProductReviewResponse(
        String id,
        String code,
        String name,
        String description,
        List<ReviewResponse> reviews
) {
    public static ProductReviewResponse from(final ProductReview productReview) {
        return new ProductReviewResponse(productReview.id(), productReview.code()
                , productReview.name(), productReview.description(), productReview.reviews().stream().map(ReviewResponse::from).toList());
    }

}
