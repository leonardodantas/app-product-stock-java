package com.product.stock.infra.feign.client;

import com.product.stock.config.FeignConfig;
import com.product.stock.infra.feign.jsons.ProductReviewFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FindReviewFeign", url = "${url.review}", configuration = FeignConfig.class)
public interface IFindReviewFeign {

    @GetMapping("product/{productCode}")
    ResponseEntity<Page<ProductReviewFeignResponse>> findByProductCode(@PathVariable final String productCode);
}
