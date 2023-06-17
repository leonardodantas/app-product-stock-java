package com.product.stock.infra.http.jsons.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record PaginatedResult<T>(int currentPage, int totalPages, int pageSize, long totalElements, List<T> elements) {

    public static <T> PaginatedResult<T> from(final Page<T> page) {
        return new PaginatedResult<>(page.getPageable().getPageNumber(), page.getTotalPages(), page.getSize(), page.getTotalElements(), page.getContent());
    }
}