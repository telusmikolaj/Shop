package pl.waw.great.shop.service;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.waw.great.shop.exception.ProductWithGivenIdNotExistsException;
import pl.waw.great.shop.exception.ProductWithGivenTitleExists;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.model.dto.ProductDTO;
import pl.waw.great.shop.model.mapper.ProductMapper;
import pl.waw.great.shop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.*;


import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private static final String PRODUCT_TITLE = "iPhone 14";

    private static final String PRODUCT_TITLE_2 = "Samsung Galaxy S22";

    private static final String DESCRIPTION = "The iPhone is a line of smartphones by Apple";

    private static final String DESCRIPTION_2 = "The Samsung Galaxy is a line of smartphones by Samsung";

    private static final BigDecimal PRICE = BigDecimal.valueOf(999);

    private static final BigDecimal PRICE_2 = BigDecimal.valueOf(899);

    private static final Long NOT_EXISTING_ID = 500L;
    @Mock
    private ProductRepository productRepository;
    private AutoCloseable autoCloseable;

    @Spy
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    @InjectMocks
    ProductService productService;


    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private Product product;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.product = new Product(PRODUCT_TITLE, DESCRIPTION, PRICE);
    }

    @AfterEach()
    void tearDown() throws Exception {
        autoCloseable.close();
    }


    @Test
    void createProduct() {
        when(this.productRepository.createProduct(any())).thenReturn(this.product);
        ProductDTO createdProduct = this.productService.createProduct(new ProductDTO(PRODUCT_TITLE, DESCRIPTION, PRICE));
        assertEquals(PRODUCT_TITLE, createdProduct.getTitle());
        assertEquals(DESCRIPTION, createdProduct.getDescription());
        assertEquals(PRICE, createdProduct.getPrice());
    }

    @Test
    void createProductWithDuplicateTitleShouldThrowException() {
        ProductDTO dto = new ProductDTO(PRODUCT_TITLE, DESCRIPTION, PRICE);
        when(this.productRepository.findProductByTitle(anyString())).thenReturn(Optional.of(this.product));

        Assertions.assertThrows(ProductWithGivenTitleExists.class, () -> {
            this.productService.createProduct(dto);
        });
    }

    @Test
    void updateProduct() {
    }

    @Test
    void updateWithNotExistingIdShouldThrowException() {
        ProductDTO dto = new ProductDTO(PRODUCT_TITLE, DESCRIPTION, PRICE);
        when(this.productRepository.getProduct(anyLong())).thenReturn(this.product);
        Assertions.assertThrows(ProductWithGivenIdNotExistsException.class, () -> {
            this.productService.updateProduct(NOT_EXISTING_ID, dto);
        });
    }

    @Test
    void updateToDuplicateTitleShouldThrowException() {
        ProductDTO dto = new ProductDTO(PRODUCT_TITLE, DESCRIPTION, PRICE);
        when(this.productRepository.findProductByTitle(anyString())).thenReturn(Optional.of(this.product));
        Assertions.assertThrows(ProductWithGivenIdNotExistsException.class, () -> {
            this.productService.updateProduct(1L, dto);
        });
    }

    @Test
    void getProduct() {
        when(this.productRepository.getProduct(anyLong())).thenReturn(this.product);
        ProductDTO productDTO = this.productService.getProduct(1L);

        assertEquals(productDTO.getTitle(), this.product.getTitle());
    }

    @Test
    void getWithNotExistingIdShouldThrowException() {
        ProductDTO dto = new ProductDTO(PRODUCT_TITLE, DESCRIPTION, PRICE);
        when(this.productRepository.getProduct(anyLong())).thenReturn(null);
        Assertions.assertThrows(ProductWithGivenIdNotExistsException.class, () -> {
            this.productService.updateProduct(NOT_EXISTING_ID, dto);
        });
    }

    @Test
    void findAllProducts() {
        when(this.productRepository.findAllProducts()).thenReturn(Collections.singletonList(this.product));
        List<ProductDTO> allProducts = this.productService.findAllProducts();
        assertEquals(1, allProducts.size());
    }

    @Test
    void deleteProduct() {
        when(this.productRepository.deleteProduct(anyLong())).thenReturn(true);
        boolean isDeleted = this.productService.deleteProduct(1L);

        assertTrue(isDeleted);
    }

    @Test
    void deleteAllProducts() {
        when(this.productRepository.deleteAll()).thenReturn(true);
        boolean isDeleted = this.productService.deleteAllProducts();

        assertTrue(isDeleted);
    }
}