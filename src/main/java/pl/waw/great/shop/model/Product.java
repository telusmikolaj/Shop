package pl.waw.great.shop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Product(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public Product() {
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }


    public static final class ProductBuilder {
        private Long id;
        private String title;
        private String description;
        private BigDecimal price;
        private LocalDateTime created;
        private LocalDateTime updated;

        private ProductBuilder() {
        }

        public static ProductBuilder builder() {
            return new ProductBuilder();
        }

        public ProductBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ProductBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductBuilder withCreated(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public ProductBuilder withUpdated(LocalDateTime updated) {
            this.updated = updated;
            return this;
        }

        public Product build() {
            Product product = new Product(title, description, price);
            product.id = this.id;
            product.updated = LocalDateTime.now();
            product.created = LocalDateTime.now();
            return product;
        }
    }
}
