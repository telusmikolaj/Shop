package pl.waw.great.shop.controller;

import org.springframework.web.bind.annotation.*;
import pl.waw.great.shop.model.dto.ProductDTO;
import pl.waw.great.shop.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return this.productService.createProduct(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        return this.productService.updateProduct(id, productDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return this.productService.getProduct(id);
    }

    @GetMapping
    public List<ProductDTO> findAllProducts() {
        return this.productService.findAllProducts();
    }

    @DeleteMapping("/{id}")
    public boolean deleteProductById(@PathVariable Long id) {
        return this.productService.deleteProduct(id);
    }

    @DeleteMapping
    public boolean deleteAllProducts() {
        return this.productService.deleteAllProducts();
    }

}
