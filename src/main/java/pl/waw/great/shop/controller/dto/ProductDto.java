package pl.waw.great.shop.controller.dto;

import java.math.BigDecimal;

public class ProductDto {
    private final String title;
    private final String description;
    private final BigDecimal price;

    public ProductDto(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static final class ProductDtoBuilder {
        private Long id;
        private String title;
        private String description;
        private BigDecimal price;

        private ProductDtoBuilder() {
        }

        public static ProductDtoBuilder builder() {
            return new ProductDtoBuilder();
        }

        public ProductDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ProductDtoBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ProductDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductDtoBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(title, description, price);
        }
    }
}
