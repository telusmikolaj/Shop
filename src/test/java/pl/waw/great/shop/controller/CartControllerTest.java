package pl.waw.great.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.waw.great.shop.config.CategoryType;
import pl.waw.great.shop.model.Category;
import pl.waw.great.shop.model.OrderLineItem;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.model.dto.OrderLineDto;
import pl.waw.great.shop.model.dto.ProductDTO;
import pl.waw.great.shop.repository.Cart;
import pl.waw.great.shop.repository.CategoryRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    private static final String PRODUCT_NAME = "iPhone 14";

    private static final String DESCRIPTION = "The iPhone is a line of smartphones by Apple";

    private static final BigDecimal PRICE = BigDecimal.valueOf(999);

    private static  final Long QUANTITY = 5L;

    @Autowired
    private Cart cart;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private OrderLineItem orderLineItem;

    private List<OrderLineItem> cartItems;

    private Category category;

    private Product product;


    @BeforeEach
    void setUp() {
        this.category = categoryRepository.findCategoryByName(CategoryType.ELEKTRONIKA);
        this.product = new Product(PRODUCT_NAME, DESCRIPTION, PRICE, this.category, QUANTITY);
        this.orderLineItem = new OrderLineItem(this.product, 2L);
        this.cartItems = this.cart.add(this.orderLineItem);
    }

    @Test
    void getCart() throws Exception {
        MvcResult result = sendRequest(MockMvcRequestBuilders.get("/cart")
                .content(String.valueOf(MediaType.APPLICATION_JSON)), HttpStatus.OK);

        List<OrderLineDto> orderLineDtos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, OrderLineDto.class));

        assertNotNull(orderLineDtos);
        assertEquals(PRODUCT_NAME, orderLineDtos.get(0).getProductTitle());
    }

    @Test
    void clearCart() throws Exception {
        MvcResult result = sendRequest(MockMvcRequestBuilders.delete("/cart")
                .content(String.valueOf(MediaType.APPLICATION_JSON)), HttpStatus.OK);

        boolean isDeleted = objectMapper.readValue(result.getResponse().getContentAsString(), Boolean.class);
        assertTrue(isDeleted);
        assertEquals(0, this.cart.size());
    }

    @Test
    void removeItem() throws Exception {
        MvcResult result = sendRequest(MockMvcRequestBuilders.delete("/cart/" + 0)
                .content(String.valueOf(MediaType.APPLICATION_JSON)), HttpStatus.OK);

        List<OrderLineDto> orderLineDtos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, OrderLineDto.class));

        assertEquals(0, orderLineDtos.size());
    }

    private MvcResult sendRequest(RequestBuilder request, HttpStatus expectedStatus) throws Exception {
        return mockMvc.perform(request)
                .andExpect(status().is(expectedStatus.value()))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}