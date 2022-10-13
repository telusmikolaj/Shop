package pl.waw.great.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.waw.great.shop.exception.ProductWithGivenIdNotExistsException;
import pl.waw.great.shop.exception.ProductWithGivenTitleExists;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.model.dto.ProductDTO;
import pl.waw.great.shop.model.mapper.ProductMapper;
import pl.waw.great.shop.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl  {
    @Autowired
    private ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
