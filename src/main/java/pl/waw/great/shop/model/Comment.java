package pl.waw.great.shop.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String text;

    private int index;

    @ManyToOne
    private Product product;
    public Comment() {
    }

    public Comment(String name, String email, String text) {
        this.name = name;
        this.email = email;
        this.text = text;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(name, comment.name) && Objects.equals(email, comment.email) && Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, text);
    }
}
