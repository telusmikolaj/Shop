package pl.waw.great.shop.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.waw.great.shop.model.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() {
        Product product = new Product("mlotek", "wbija", BigDecimal.ONE);
        Product saved = productRepository.create(product);
        assertNotNull(saved);
    }

}