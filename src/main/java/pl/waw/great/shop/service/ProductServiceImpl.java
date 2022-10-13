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
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        this.productRepository.findProductByTitle(productDTO.getTitle())
                .ifPresent(a -> {
                    throw new ProductWithGivenTitleExists(productDTO.getTitle());
                });

        Product createdProduct = this.productRepository.createProduct(productMapper.dtoToProduct(productDTO));
        return productMapper.productToDto(createdProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO newProduct) {
        newProduct.setId(id);
        this.getProduct(newProduct.getId());
        this.productRepository.findProductByTitle(newProduct.getTitle())
                .ifPresent(a -> {
                    throw new ProductWithGivenTitleExists(newProduct.getTitle());
                });

        Product updatedProduct = this.productRepository.updateProduct(productMapper.dtoToProduct(newProduct));
        return productMapper.productToDto(updatedProduct);
    }

    @Override
    public ProductDTO getProduct(Long id) {
        Product product = this.productRepository.getProduct(id)
                .orElseThrow(() -> new ProductWithGivenIdNotExistsException(id));

        return productMapper.productToDto(product);
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return this.productRepository.findAllProducts()
                .stream()
                .map(productMapper::productToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteProduct(Long id) {
        return this.productRepository.deleteProduct(id);
    }

    @Override
    public boolean deleteAllProducts() {
        return this.productRepository.deleteAll();
    }

}
