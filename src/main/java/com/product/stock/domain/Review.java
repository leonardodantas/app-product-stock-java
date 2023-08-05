package com.product.stock.domain;

import java.time.LocalDateTime;

public record Review(
        String id,
        String name,
        String description,
        int rating,
        LocalDateTime date
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private int rating;
        private LocalDateTime date;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder rating(final int rating) {
            this.rating = rating;
            return this;
        }

        public Builder date(final LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Review build() {
            return new Review(id, name, description, rating, date);
        }
    }
}
