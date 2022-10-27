package pl.waw.great.shop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.waw.great.shop.config.CategoryType;
import pl.waw.great.shop.model.Category;
import pl.waw.great.shop.model.OrderLineItem;
import pl.waw.great.shop.model.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartTest {

    private static final String PRODUCT_NAME = "iPhone 14";

    private static final String DESCRIPTION = "The iPhone is a line of smartphones by Apple";

    private static final BigDecimal PRICE = BigDecimal.valueOf(999);

    private static  final Long QUANTITY = 5L;
    private OrderLineItem orderLineItem;

    private Category category;

    private Product product;

    private List<OrderLineItem> cartItems;
    @Autowired
    private Cart cart;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        this.category = categoryRepository.findCategoryByName(CategoryType.ELEKTRONIKA);
        this.product = new Product(PRODUCT_NAME, DESCRIPTION, PRICE, this.category, QUANTITY);
        this.orderLineItem = new OrderLineItem(this.product, 2L);
        this.cartItems = this.cart.add(this.orderLineItem);
    }

    @AfterEach
    void tearDown() {
        this.cart.clear();
    }


    @Test
    void add() {
        assertNotNull(this.cartItems);
        assertEquals(1, this.cartItems.size());
    }

    @Test
    void getCartItems() {
        List<OrderLineItem> cartItems1 = this.cart.getCartItems();

        assertEquals(this.cartItems, cartItems1);
    }

    @Test
    void removeItem() {
        boolean isDeleted = this.cart.removeItem(0);
        assertTrue(isDeleted);
        assertEquals(0, this.cartItems.size());
    }

    @Test
    void clear() {
        boolean isCleared = this.cart.clear();
        assertTrue(isCleared);
        assertEquals(0, this.cartItems.size());
    }

    @Test
    void size() {
        assertEquals(1, this.cart.size());
    }
}