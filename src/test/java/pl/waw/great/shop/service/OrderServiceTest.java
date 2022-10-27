package pl.waw.great.shop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import pl.waw.great.shop.config.CategoryType;
import pl.waw.great.shop.model.*;
import pl.waw.great.shop.model.dto.OrderDto;
import pl.waw.great.shop.model.mapper.OrderLineMapper;
import pl.waw.great.shop.model.mapper.OrderMapper;
import pl.waw.great.shop.repository.*;

import javax.naming.Name;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderServiceTest {

    private static final String PRODUCT_NAME = "iPhone 14";

    private static final String DESCRIPTION = "The iPhone is a line of smartphones by Apple";

    private static final BigDecimal PRICE = BigDecimal.valueOf(999);
    private static final Long QUANTITY = 5L;

    public static final String NAME = "Mikolaj";

    private Category category;

    private Product product;

    private User user;

    private Order order;

    private OrderLineItem orderLineItem;

    private List<OrderLineItem> cartItems = new ArrayList<>();

    private Cart cart = mock(Cart.class);

    private UserRepository userRepository = mock(UserRepository.class);

    private OrderRepository orderRepository = mock(OrderRepository.class);

    private ProductRepository productRepository = mock(ProductRepository.class);
    @InjectMocks
    private OrderService orderService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Spy
    OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Spy
    OrderLineMapper orderLineMapper = Mappers.getMapper(OrderLineMapper.class);

    @BeforeEach
    void setUp() {
        this.user = new User(NAME);
        this.category = categoryRepository.findCategoryByName(CategoryType.ELEKTRONIKA);
        this.product = new Product(PRODUCT_NAME, DESCRIPTION, PRICE, this.category, QUANTITY);
        this.orderLineItem = new OrderLineItem(this.product, 2L);
        this.cartItems.add(this.orderLineItem);
        this.order = new Order(BigDecimal.ONE, this.user, this.cartItems, LocalDateTime.now());

        ReflectionTestUtils.setField(
                orderMapper,
                "orderLineMapper",
                orderLineMapper
        );
    }

    @Test
    void createOrder() {
        when(this.cart.getCartItems()).thenReturn(this.cartItems);
        when(this.orderRepository.create(any())).thenReturn(this.order);
        when(this.userRepository.findUserByTitle(anyString())).thenReturn(Optional.of(this.user));
        OrderDto order = this.orderService.createOrder(NAME);

        assertNotNull(order);
        assertEquals(NAME, order.getUserName());
    }
}