package com.product.stock.infra.feign;

import com.product.stock.app.rest.IFindReviewRest;
import com.product.stock.domain.ProductReview;
import com.product.stock.infra.http.converters.ProductRequestConverter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FindReviewService implements IFindReviewRest {

    private final IFindReviewFeign findReviewFeign;
    private final ProductReviewFeignConverter productReviewFeignConverter;

    public FindReviewService(final IFindReviewFeign findReviewFeign, final ProductReviewFeignConverter productReviewFeignConverter) {
        this.findReviewFeign = findReviewFeign;
        this.productReviewFeignConverter = productReviewFeignConverter;
    }

    @Override
    public Page<ProductReview> findByProductId(final String productId) {
        final var response = findReviewFeign.findByProductId(productId).getBody();
        return response.map(productReviewFeignConverter::convert);
    }
}
