package com.product.stock.infra.feign.jsons;

import java.time.LocalDateTime;

public record ReviewFeignResponse(
        String id,
        String name,
        String description,
        int rating,
        LocalDateTime date
) {

}
