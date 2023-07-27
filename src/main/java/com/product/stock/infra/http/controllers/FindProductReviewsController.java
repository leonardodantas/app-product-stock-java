package com.product.stock.infra.http.controllers;

import com.product.stock.app.usecases.FindProductReviews;
import com.product.stock.infra.http.jsons.response.PaginatedResult;
import com.product.stock.infra.http.jsons.response.ProductReviewResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/reviews")
public class FindProductReviewsController {

    private final FindProductReviews findProductReviews;

    public FindProductReviewsController(final FindProductReviews findProductReviews) {
        this.findProductReviews = findProductReviews;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("product/{productCode}")
    public PaginatedResult<ProductReviewResponse> findByProductId(@PathVariable final String productCode) {
        final var response = findProductReviews.findReviewsByProductId(productCode);
        return PaginatedResult.from(response.map(ProductReviewResponse::from));
    }
}
