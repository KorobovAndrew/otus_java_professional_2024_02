package ru.otus.java.pro.homework05.builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private final Long id;
    private final String title;
    private final String description;
    private final Double cost;
    private final Double weight;
    private final Double width;
    private final Double length;
    private final Double height;


    private Product(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.cost = builder.cost;
        this.weight = builder.weight;
        this.width = builder.width;
        this.length = builder.length;
        this.height = builder.height;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String title;
        private String description;
        private Double cost;
        private Double weight;
        private Double width;
        private Double length;
        private Double height;

        private Builder() {
        }

        public Product build() {
            return new Product(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder cost(Double cost) {
            this.cost = cost;
            return this;
        }

        public Builder weight(Double weight) {
            this.weight = weight;
            return this;
        }

        public Builder width(Double width) {
            this.width = width;
            return this;
        }

        public Builder length(Double length) {
            this.length = length;
            return this;
        }

        public Builder height(Double height) {
            this.height = height;
            return this;
        }
    }
}
