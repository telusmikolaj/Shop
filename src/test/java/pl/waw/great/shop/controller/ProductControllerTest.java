package pl.waw.great.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
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
import pl.waw.great.shop.exception.ErrorInfo;
import pl.waw.great.shop.model.dto.ProductDTO;
import pl.waw.great.shop.repository.ProductRepository;
import pl.waw.great.shop.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    private static final String PRODUCT_TITLE = "iPhone 14";

    private static final String PRODUCT_TITLE_2 = "Samsung Galaxy S22";

    private static final String DESCRIPTION = "The iPhone is a line of smartphones by Apple";

    private static final String DESCRIPTION_2 = "The Samsung Galaxy is a line of smartphones by Samsung";

    private static final BigDecimal PRICE = BigDecimal.valueOf(999);

    private static final BigDecimal PRICE_2 = BigDecimal.valueOf(899);

    private static final Long NOT_EXISTING_ID = 500L;

    private ProductDTO productDTO;

    private ProductDTO toUpdateDto;

    private Long createdProductId;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        this.productDTO = new ProductDTO(PRODUCT_TITLE, DESCRIPTION, PRICE);
        this.toUpdateDto = new ProductDTO(PRODUCT_TITLE_2, DESCRIPTION_2, PRICE_2);
        this.createdProductId = this.productService.createProduct(this.toUpdateDto).getId();
    }

    @AfterEach
    void tearDown() {
        this.productService.deleteAllProducts();
    }

    @Test
    void createProduct() throws Exception {
        String productDtoAsJson = objectMapper.writeValueAsString(this.productDTO);

        MvcResult result = sendRequest(MockMvcRequestBuilders.post("/product")
                .content(productDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.OK);

        ProductDTO createdProductDto = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDTO.class);
        assertNotNull(createdProductDto);
        assertEquals(this.productDTO, createdProductDto);
    }

    @Test
    void createProductWithDuplicateTitleShouldThrowException() throws Exception {
        String productDtoAsJson = objectMapper.writeValueAsString(this.toUpdateDto);

        MvcResult result = sendRequest(MockMvcRequestBuilders.post("/product")
                .content(productDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.CONFLICT);


        ErrorInfo exceptionDtoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorInfo.class);
        assertEquals("Product with title: " + PRODUCT_TITLE_2 + " already exists", exceptionDtoResponse.getMessage());
    }

    @Test
    void createWithBlankTitleShouldThrowException() throws Exception {
        ProductDTO dtoWithBlankTitle = new ProductDTO("", DESCRIPTION, PRICE_2);
        String productDtoAsJson = objectMapper.writeValueAsString(dtoWithBlankTitle);

        MvcResult result = sendRequest(MockMvcRequestBuilders.post("/product")
                .content(productDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.BAD_REQUEST);

        ErrorInfo exceptionDtoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorInfo.class);
        assertEquals("Title cannot be blank", exceptionDtoResponse.getMessage());
    }

    @Test
    void createWithBlankDescriptionShouldThrowException() throws Exception {
        ProductDTO dtoWithBlankDescription = new ProductDTO(PRODUCT_TITLE, "", PRICE_2);
        String productDtoAsJson = objectMapper.writeValueAsString(dtoWithBlankDescription);

        MvcResult result = sendRequest(MockMvcRequestBuilders.post("/product")
                .content(productDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.BAD_REQUEST);

        ErrorInfo exceptionDtoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorInfo.class);
        assertEquals("Description cannot be blank", exceptionDtoResponse.getMessage());
    }

    @Test
    void createWithPriceZeroException() throws Exception {
        ProductDTO dtoWithPriceZero = new ProductDTO(PRODUCT_TITLE, DESCRIPTION, BigDecimal.ZERO);
        String productDtoAsJson = objectMapper.writeValueAsString(dtoWithPriceZero);

        MvcResult result = sendRequest(MockMvcRequestBuilders.post("/product")
                .content(productDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.BAD_REQUEST);

        ErrorInfo exceptionDtoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorInfo.class);
        assertEquals("Price must be higher than 1", exceptionDtoResponse.getMessage());
    }


    @Test
    void updateProduct() throws Exception {
        String productDtoAsJson = objectMapper.writeValueAsString(this.productDTO);

        MvcResult result = sendRequest(MockMvcRequestBuilders.put("/product/" + this.createdProductId)
                .content(productDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.OK);

        ProductDTO updatedDto = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDTO.class);
        assertNotNull(updatedDto);
        assertEquals(updatedDto, this.productDTO);
    }

    @Test
    void updateToDuplicateTitleShouldThrowException() throws Exception {
        ProductDTO newData = new ProductDTO(PRODUCT_TITLE_2, "TEST DESCRIPTION", BigDecimal.valueOf(1500));
        String productDtoAsJson = objectMapper.writeValueAsString(newData);

        MvcResult result = sendRequest(MockMvcRequestBuilders.put("/product/" + this.createdProductId)
                .content(productDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.CONFLICT);

        ErrorInfo exceptionDtoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorInfo.class);
        assertEquals("Product with title: " + PRODUCT_TITLE_2 + " already exists", exceptionDtoResponse.getMessage());
    }

    @Test
    void updateWithNotExistingIdShouldThrowException() throws Exception {
        ProductDTO newData = new ProductDTO("NEW TITLE", "TEST DESCRIPTION", BigDecimal.valueOf(1500));
        String productDtoAsJson = objectMapper.writeValueAsString(newData);

        MvcResult result = sendRequest(MockMvcRequestBuilders.put("/product/" + NOT_EXISTING_ID)
                .content(productDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.CONFLICT);

        ErrorInfo exceptionDtoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorInfo.class);
        assertEquals("Product with id: " + NOT_EXISTING_ID + " not exists", exceptionDtoResponse.getMessage());
    }

    @Test
    void getProductById() throws Exception {
        MvcResult result = sendRequest(MockMvcRequestBuilders.get("/product/" + this.createdProductId)
                .content(String.valueOf(MediaType.APPLICATION_JSON)), HttpStatus.OK);

        ProductDTO savedDto = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDTO.class);
        assertNotNull(savedDto);
        assertEquals(savedDto.getTitle(), this.toUpdateDto.getTitle());
    }

    @Test
    void getWithNotExistingIdShouldThrowException() throws Exception {
        MvcResult result = sendRequest(MockMvcRequestBuilders.get("/product/" + NOT_EXISTING_ID)
                .content(String.valueOf(MediaType.APPLICATION_JSON)), HttpStatus.CONFLICT);

        ErrorInfo exceptionDtoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorInfo.class);
        assertEquals("Product with id: " + NOT_EXISTING_ID + " not exists", exceptionDtoResponse.getMessage());
    }

    @Test
    void findAllProducts() throws Exception {
        MvcResult result = sendRequest(MockMvcRequestBuilders.get("/product")
                .content(String.valueOf(MediaType.APPLICATION_JSON)), HttpStatus.OK);

        List<ProductDTO> allProducts = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, ProductDTO.class));

        assertNotNull(allProducts);
        assertEquals(1, allProducts.size());
    }

    @Test
    void deleteProductById() throws Exception {
        MvcResult result = sendRequest(MockMvcRequestBuilders.delete("/product/" + this.createdProductId)
                .content(String.valueOf(MediaType.APPLICATION_JSON)), HttpStatus.OK);

        boolean isDeleted = objectMapper.readValue(result.getResponse().getContentAsString(), Boolean.class);
        assertTrue(isDeleted);
    }

    @Test
    void deleteAllProducts() throws Exception {
        MvcResult result = sendRequest(MockMvcRequestBuilders.delete("/product")
                .content(String.valueOf(MediaType.APPLICATION_JSON)), HttpStatus.OK);

        boolean isDeleted = objectMapper.readValue(result.getResponse().getContentAsString(), Boolean.class);
        assertTrue(isDeleted);
    }

    private MvcResult sendRequest(RequestBuilder request, HttpStatus expectedStatus) throws Exception {
        return mockMvc.perform(request)
                .andExpect(status().is(expectedStatus.value()))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}