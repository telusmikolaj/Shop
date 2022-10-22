package pl.waw.great.shop.service;

import org.springframework.stereotype.Service;
import pl.waw.great.shop.exception.InvalidCommentIndexException;
import pl.waw.great.shop.exception.ProductWithGivenTitleNotExistsException;
import pl.waw.great.shop.model.Comment;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.model.dto.CommentDto;
import pl.waw.great.shop.model.mapper.CommentMapper;
import pl.waw.great.shop.repository.CommentRepository;
import pl.waw.great.shop.repository.ProductRepository;

import java.util.List;

@Service
public class CommentService {

    private final ProductRepository productRepository;

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    public CommentService(ProductRepository productRepository, CommentRepository commentRepository, CommentMapper commentMapper) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public CommentDto createComment(String productTitle, CommentDto commentDto) {
        Product commentedProduct = this.productRepository.findProductByTitle(productTitle)
                .orElseThrow(() -> new ProductWithGivenTitleNotExistsException(productTitle));
        Comment comment = commentMapper.dtoToComment(commentDto);

        int index = commentedProduct.addComment(comment);
        comment.setIndex(index);
        this.commentRepository.create(comment);
        commentDto.setIndex(index);
        return commentDto;
    }

    public boolean delete(String productTitle, int index) {
        Product commentedProduct = this.productRepository.findProductByTitle(productTitle)
                .orElseThrow(() -> new ProductWithGivenTitleNotExistsException(productTitle));
        List<Comment> productComments = commentedProduct.getCommentsList();

        if (index > productComments.size() - 1 || index < 0) {
            throw new InvalidCommentIndexException(index);
        }
        Long idToDelete = productComments.get(index).getId();
        productComments.remove(index);
        return this.commentRepository.delete(idToDelete);
    }
}
