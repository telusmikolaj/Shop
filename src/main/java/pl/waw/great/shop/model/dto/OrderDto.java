package pl.waw.great.shop.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {


    private List<OrderLineDto> orderLineItemList;

    private String userName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal totalPrice;

    public OrderDto() {
    }

    public OrderDto(List<OrderLineDto> orderLineItemList, String userName) {
        this.orderLineItemList = orderLineItemList;
        this.userName = userName;
    }

    public List<OrderLineDto> getOrderLineItemList() {
        return orderLineItemList;
    }

    public void setOrderLineItemList(List<OrderLineDto> orderLineItemList) {
        this.orderLineItemList = orderLineItemList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
