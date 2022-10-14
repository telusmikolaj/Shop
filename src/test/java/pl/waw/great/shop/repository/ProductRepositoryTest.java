package pl.waw.great.shop.repository;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.waw.great.shop.model.Category;
import pl.waw.great.shop.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    private static final String PRODUCT_NAME = "iPhone 14";

    private static final String PRODUCT_NAME_2 = "Samsung Galaxy S22";
    private static final String DESCRIPTION = "The iPhone is a line of smartphones by Apple";

    private static final String DESCRIPTION_2 = "The Samsung Galaxy is a line of smartphones by Samsung";
    private static final BigDecimal PRICE = BigDecimal.valueOf(999);

    private static final BigDecimal PRICE_2 = BigDecimal.valueOf(899);
    private static final Category CATEGORY = new Category();

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product(PRODUCT_NAME, DESCRIPTION, PRICE, null);
        this.productRepository.createProduct(this.product);
    }

    @AfterEach
    void tearDown() {
        this.productRepository.deleteAll();
    }

    @Test
    void create() {
        assertNotNull(this.product.getId());
        this.productRepository.createProduct(new Product("title", "ddses", BigDecimal.valueOf(15), null));
    }

    @Test
    void get() {
        Product savedProduct = this.productRepository.getProduct(this.product.getId());
        assertNotNull(savedProduct);
    }

    @Test
    void delete() {
        boolean isDeleted = this.productRepository.deleteProduct(this.product.getId());
        assertTrue(isDeleted);
    }

    @Test
    void update() {
        Product newProduct = new Product(PRODUCT_NAME_2, DESCRIPTION_2, PRICE_2, null);
        newProduct.setId(this.product.getId());
        Product updatedProduct = this.productRepository.updateProduct(newProduct);
        assertEquals(updatedProduct, newProduct);
    }

    @Test
    void findAllProducts() {
        this.productRepository.createProduct(new Product(PRODUCT_NAME_2, DESCRIPTION_2, PRICE_2, null ));
        List<Product> allProducts = this.productRepository.findAllProducts();
        assertEquals(2, allProducts.size());
    }

    @Test
    void findProductByTitle() {
        Optional<Product> productByTitle = this.productRepository.findProductByTitle(PRODUCT_NAME);

        assertNotNull(productByTitle);
    }


}