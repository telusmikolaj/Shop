package pl.waw.great.shop.model.dto;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;


public class ProductDTO {

    private Long id;
    @NotBlank(message = "{titleNotBlank}")
    private String title;
    @NotBlank(message = "{descriptionNotBlank}")
    private String description;
    @NotNull
    @Min(value = 1, message = "{priceMinValue}")
    private BigDecimal price;

    @NotBlank(message = "{categoryNotBlank}")
    private String categoryName;

    public ProductDTO() {
    }

    public ProductDTO(String title, String description, BigDecimal price, String categoryName) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO)) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price);
    }
}
