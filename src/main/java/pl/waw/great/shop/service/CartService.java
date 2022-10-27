package pl.waw.great.shop.service;

import org.springframework.stereotype.Service;
import pl.waw.great.shop.exception.InsufficientProductQuantityException;
import pl.waw.great.shop.exception.InvalidCartItemIndexException;
import pl.waw.great.shop.exception.ProductWithGivenTitleNotExistsException;
import pl.waw.great.shop.model.OrderLineItem;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.model.dto.OrderLineDto;
import pl.waw.great.shop.model.mapper.OrderLineMapper;
import pl.waw.great.shop.repository.Cart;
import pl.waw.great.shop.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final ProductRepository productRepository;

    private final Cart cart;
    private final OrderLineMapper orderLineMapper;

    public CartService(ProductRepository productRepository, Cart cart, OrderLineMapper orderLineMapper) {
        this.productRepository = productRepository;
        this.cart = cart;
        this.orderLineMapper = orderLineMapper;
    }

    public List<OrderLineDto> create(String productTitle, Long amount) {
        Product product = this.productRepository.findProductByTitle(productTitle)
                .orElseThrow(() -> new ProductWithGivenTitleNotExistsException(productTitle));

        if (amount > product.getQuantity()) {
                throw new InsufficientProductQuantityException(productTitle, product.getQuantity());
        }
        OrderLineItem orderLineItem = new OrderLineItem(product, amount);
        List<OrderLineItem> itemInCart = this.cart.add(orderLineItem);
        return itemInCart.stream().map(orderLineMapper::orderLineToDto).collect(Collectors.toList());
    }

    public List<OrderLineDto> removeItem(int index) {
        if (index < 0 || index > this.cart.size()) {
            throw new InvalidCartItemIndexException();
        }
        this.cart.removeItem(index);
        List<OrderLineItem> orderItems = this.cart.getCartItems();
        return orderItems.stream().map(orderLineMapper::orderLineToDto).collect(Collectors.toList());
    }
    public List<OrderLineDto> get()  {
        List<OrderLineItem> orderItems = this.cart.getCartItems();
        return orderItems.stream().map(orderLineMapper::orderLineToDto).collect(Collectors.toList());
    }
    public boolean clear() {
        return this.cart.clear();
    }

}
