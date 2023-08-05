package com.product.stock.infra.feign.jsons;

import java.util.List;

public record ProductReviewFeignResponse (
        String id,
        String code,
        String name,
        String description,
        List<ReviewFeignResponse> reviews
) {
}
