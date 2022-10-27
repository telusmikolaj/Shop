package pl.waw.great.shop.controller;

import org.springframework.web.bind.annotation.*;
import pl.waw.great.shop.model.dto.OrderLineDto;
import pl.waw.great.shop.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<OrderLineDto> getCart() {
        return this.cartService.get();
    }

    @DeleteMapping()
    public boolean clearCart() {
        return this.cartService.clear();
    }

    @DeleteMapping("/{index}")
    public List<OrderLineDto> removeItem(@PathVariable int index) {
        return this.cartService.removeItem(index);
    }
}
