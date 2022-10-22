package pl.waw.great.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pl.waw.great.shop.exception.InvalidCommentIndexException;
import pl.waw.great.shop.exception.ProductWithGivenTitleExists;
import pl.waw.great.shop.exception.ProductWithGivenTitleNotExistsException;
import pl.waw.great.shop.model.Category;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.model.dto.CommentDto;
import pl.waw.great.shop.model.mapper.CommentMapper;
import pl.waw.great.shop.model.mapper.ProductMapper;
import pl.waw.great.shop.repository.CommentRepository;
import pl.waw.great.shop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CommentServiceTest {
    private static final String PRODUCT_TITLE = "iPhone 14";
    private static final String DESCRIPTION = "The iPhone is a line of smartphones by Apple";
    private static final BigDecimal PRICE = BigDecimal.valueOf(999);

    private final static String TEST_NAME = "USER";

    private final static String TEST_EMAIL = "example@gmail.com";

    private final static String TEST_TEXT = "TLDR";

    private  Product product;

    private CommentDto comment;

    ProductRepository productRepository = mock(ProductRepository.class);

    CommentRepository commentRepository = mock(CommentRepository.class);
    @InjectMocks
    private CommentService commentService;

    @Spy
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Spy
    CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);

    @BeforeEach
    void setUp() {
        this.product = new Product(PRODUCT_TITLE, DESCRIPTION, PRICE, null);
        this.comment = new CommentDto(TEST_NAME, TEST_EMAIL, TEST_TEXT);
        this.product.addComment(commentMapper.dtoToComment(this.comment));
    }

    @Test
    void createComment() {
        when(this.productRepository.findProductByTitle(anyString())).thenReturn(Optional.of(this.product));
        CommentDto commentDto = this.commentService.createComment(PRODUCT_TITLE, this.comment);

        assertEquals(commentDto, this.comment);
    }

    @Test
    void createCommentWithNotExistingProductTitleShouldThrowException() {
        when(this.productRepository.findProductByTitle(anyString())).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(ProductWithGivenTitleNotExistsException.class, () -> {
            this.commentService.createComment("Title", this.comment);
        });
    }

    @Test
    void deleteCommentWithNotExistingProductTitleShouldThrowException() {
        when(this.productRepository.findProductByTitle(anyString())).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(ProductWithGivenTitleNotExistsException.class, () -> {
            this.commentService.delete("Title", 1);
        });
    }

    @Test
    void deleteCommentWithInvalidIndexShouldThrowException() {
        when(this.productRepository.findProductByTitle(anyString())).thenReturn(Optional.of(this.product));
        Assertions.assertThrows(InvalidCommentIndexException.class, () -> {
            this.commentService.delete("Title", 1);
        });
    }

    @Test
    void delete() {
        when(this.productRepository.findProductByTitle(anyString())).thenReturn(Optional.of(this.product));
        when(this.commentRepository.delete(any())).thenReturn(true);
        boolean isDeleted = this.commentService.delete(PRODUCT_TITLE, 0);
        assertTrue(isDeleted);

    }
}