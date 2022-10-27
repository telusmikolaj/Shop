package pl.waw.great.shop.repository;

import org.springframework.stereotype.Component;
import pl.waw.great.shop.model.OrderLineItem;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {

    private List<OrderLineItem> itemsInCart = new ArrayList<>();

    public List<OrderLineItem> add(OrderLineItem orderLineItem) {
        if (itemsInCart.contains(orderLineItem)) {
            int index = itemsInCart.indexOf(orderLineItem);
            OrderLineItem itemFromCart = itemsInCart.get(index);
            itemFromCart.setQuantity(itemFromCart.getQuantity() + orderLineItem.getQuantity());
            itemsInCart.set(index, itemFromCart);
            return itemsInCart;
        }
        this.itemsInCart.add(orderLineItem);
        return itemsInCart;
    }

    public List<OrderLineItem> getCartItems() {
        return this.itemsInCart;
    }

    public boolean removeItem(int index) {
        itemsInCart.remove(index);
        return true;
    }
    public boolean clear() {
        this.itemsInCart.clear();
        return true;
    }

    public int size() {
        return this.itemsInCart.size();
    }


}
