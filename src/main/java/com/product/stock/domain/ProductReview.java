package com.product.stock.domain;

import java.util.ArrayList;
import java.util.List;

public record ProductReview(
        String id,
        String code,
        String name,
        String description,
        List<Review> reviews
) {

    public static Builder builder(final String id, final String code, final String name, final String description) {
        return new Builder(id, code, name, description);
    }

    public static class Builder {
        private final String id;
        private final String code;
        private final String name;
        private final String description;
        private List<Review> reviews = new ArrayList<>();

        public Builder(final String id, final String code, final String name, final String description) {
            this.id = id;
            this.code = code;
            this.name = name;
            this.description = description;
        }

        public Builder reviews(final List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public ProductReview build() {
            return new ProductReview(id, code, name, description, reviews);
        }
    }
}
