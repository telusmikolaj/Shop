package pl.waw.great.shop.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "order_")
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal totalPrice;
    @ManyToOne
    private User user;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "order_id")
    private List<OrderLineItem> orderLineItemList;

    private LocalDateTime created;

    public Order(BigDecimal totalPrice, User user, List<OrderLineItem> orderLineItemList, LocalDateTime created) {
        this.totalPrice = totalPrice;
        this.user = user;
        this.orderLineItemList = orderLineItemList;
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderLineItem> getOrderLineItemList() {
        return orderLineItemList;
    }

    public void setOrderLineItemList(List<OrderLineItem> orderLineItemList) {
        this.orderLineItemList = orderLineItemList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(totalPrice, order.totalPrice) && Objects.equals(user, order.user) && Objects.equals(orderLineItemList, order.orderLineItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPrice, user, orderLineItemList);
    }
}
