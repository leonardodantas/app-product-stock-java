package com.product.stock.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public record Product(
        String id,
        String code,
        String name,
        String description,
        BigDecimal price,
        int quantity,
        LocalDateTime create,
        LocalDateTime update,
        List<String> details
) {

    @Override
    public String code() {
        return code.trim().toUpperCase();
    }

    public static Builder builder(final String code, final String name, final String description, final BigDecimal price) {
        return new Builder(code, name, description, price);
    }

    public Product of(final String id, final LocalDateTime create, final LocalDateTime update) {
        return new Product(id, this.code, this.name, this.description, this.price, this.quantity, create, update, this.details);
    }

    public static class Builder {
        private String id;
        private final String code;
        private final String name;
        private final String description;
        private final BigDecimal price;
        private int quantity;
        private LocalDateTime create;
        private LocalDateTime update;
        private List<String> details;

        public Builder(final String code, final String name, final String description, final BigDecimal price) {
            this.code = code;
            this.name = name;
            this.description = description;
            this.price = price;
            this.details = new ArrayList<>();
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder quantity(final int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder create(final LocalDateTime create) {
            this.create = create;
            return this;
        }

        public Builder update(final LocalDateTime update) {
            this.update = update;
            return this;
        }

        public Builder details(final List<String> details) {
            this.details = details;
            return this;
        }

        public Product build() {
            return new Product(id, code, name, description, price, quantity, isNull(create) ? LocalDateTime.now() : create, isNull(update) ? LocalDateTime.now() : update, details);
        }
    }
}

