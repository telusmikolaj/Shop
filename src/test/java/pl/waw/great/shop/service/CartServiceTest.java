package pl.waw.great.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.waw.great.shop.config.CategoryType;
import pl.waw.great.shop.exception.InvalidCartItemIndexException;
import pl.waw.great.shop.exception.ProductWithGivenTitleNotExistsException;
import pl.waw.great.shop.model.Category;
import pl.waw.great.shop.model.OrderLineItem;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.model.dto.OrderLineDto;
import pl.waw.great.shop.model.mapper.OrderLineMapper;
import pl.waw.great.shop.repository.Cart;
import pl.waw.great.shop.repository.CategoryRepository;
import pl.waw.great.shop.repository.ProductRepository;

import java.math.BigDecimal;
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
class CartServiceTest {

    private static final String PRODUCT_NAME = "iPhone 14";

    private static final String DESCRIPTION = "The iPhone is a line of smartphones by Apple";

    private static final BigDecimal PRICE = BigDecimal.valueOf(999);

    private static final Long QUANTITY = 5L;

    private Category category;

    private Product product;

    private OrderLineItem orderLineItem;


    private Cart cart = mock(Cart.class);

    private ProductRepository productRepository = mock(ProductRepository.class);

    private List<OrderLineItem> cartItems = new ArrayList<>();

    @InjectMocks
    private CartService cartService;

    @Spy
    private OrderLineMapper orderLineMapper = Mappers.getMapper(OrderLineMapper.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        this.category = categoryRepository.findCategoryByName(CategoryType.ELEKTRONIKA);
        this.product = new Product(PRODUCT_NAME, DESCRIPTION, PRICE, this.category, QUANTITY);
        this.orderLineItem = new OrderLineItem(this.product, 2L);
        this.cartItems.add(this.orderLineItem);
    }

    @Test
    void create() {
        when(this.productRepository.findProductByTitle(anyString())).thenReturn(Optional.of(this.product));
        when(this.cart.add(any())).thenReturn(this.cartItems);

        List<OrderLineDto> orderLineDtos = this.cartService.create(PRODUCT_NAME, 2L);
        assertNotNull(orderLineDtos);
        assertEquals(PRODUCT_NAME, orderLineDtos.get(0).getProductTitle());

    }

    @Test
    void createWithNotExistingProductShouldThrowException() {
        when(this.productRepository.findProductByTitle(anyString())).thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(ProductWithGivenTitleNotExistsException.class, () -> {
            this.cartService.create("Samsung", 1L);
        });

    }

    @Test
    void removeItem() {
        this.cartItems.remove(0);
        when(this.cart.getCartItems()).thenReturn(this.cartItems);

        List<OrderLineDto> orderLineDtos = this.cartService.removeItem(0);
        assertEquals(0, orderLineDtos.size());
    }

    @Test
    void get() {
        when(this.cart.getCartItems()).thenReturn(this.cartItems);

        List<OrderLineDto> orderLineDtos = this.cartService.get();

        assertEquals(1, orderLineDtos.size());
        assertEquals(PRODUCT_NAME, orderLineDtos.get(0).getProductTitle());
    }
}