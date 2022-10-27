package pl.waw.great.shop.controller;

import org.springframework.web.bind.annotation.*;
import pl.waw.great.shop.model.dto.OrderDto;
import pl.waw.great.shop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDto createOrder() {
        return this.orderService.createOrder();
    }

    @GetMapping
    public List<OrderDto> getUserOrders() {
        return this.orderService.getUserOrders();
    }

}
