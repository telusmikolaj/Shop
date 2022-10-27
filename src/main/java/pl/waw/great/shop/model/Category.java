package pl.waw.great.shop.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> productList;

    private LocalDateTime created;

    private LocalDateTime updated;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.created = LocalDateTime.now();
    }

    public void addProduct(Product product) {
        this.productList.add(product);
        product.setCategory(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {

        this.productList = productList;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public void setCreated() {

        this.created = LocalDateTime.now();
    }

    public void setUpdated() {

        this.updated = LocalDateTime.now();
    }
}
