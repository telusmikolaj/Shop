package pl.waw.great.shop.controller;

import org.springframework.web.bind.annotation.*;
import pl.waw.great.shop.model.dto.OrderDto;
import pl.waw.great.shop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDto createOrder() {
        return this.orderService.createOrder();
    }

    @GetMapping("/byUser/{userName}")
    public List<OrderDto> getUserOrders(@PathVariable String userName) {
        return this.orderService.getUserOrders();
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable String orderId) {
        return this.orderService.getOrderById(orderId);
    }

}
