package pl.waw.great.shop.model.dto;


import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    private String userName;

    private BigDecimal totalAmount;

    private List<CartLineItemDto> cartLineItemList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<CartLineItemDto> getCartLineItemList() {
        return cartLineItemList;
    }

    public void setCartLineItemList(List<CartLineItemDto> cartLineItemList) {
        this.cartLineItemList = cartLineItemList;
    }
}
