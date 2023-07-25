package com.product.stock.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8080/reviews")
public interface IFindReviewFeign {

    @GetMapping("product/{productId}")
    ResponseEntity<Page<ProductReviewFeignResponse>> findByProductId(final String productId);
}
