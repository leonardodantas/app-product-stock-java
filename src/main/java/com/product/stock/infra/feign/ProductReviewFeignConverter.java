package com.product.stock.infra.feign;

import com.product.stock.domain.ProductReview;
import com.product.stock.domain.Review;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductReviewFeignConverter implements Converter<ProductReviewFeignResponse, ProductReview> {

    @Override
    public ProductReview convert(final ProductReviewFeignResponse response) {
        return ProductReview.builder(
                        response.id(), response.code(), response.name(),
                        response.description())
                .reviews(getReviews(response.reviews()))
                .build();
    }

    private List<Review> getReviews(final List<ReviewFeignResponse> reviews) {
        return reviews.stream()
                .map(review ->
                        Review.builder()
                                .id(review.id())
                                .name(review.name())
                                .description(review.description())
                                .date(review.date())
                                .rating(review.rating())
                                .build()).toList();
    }
}
