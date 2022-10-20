package pl.waw.great.shop;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import pl.waw.great.shop.config.CategoryType;
import pl.waw.great.shop.model.dto.ProductDTO;
import pl.waw.great.shop.service.ProductService;

import java.math.BigDecimal;
import java.util.Random;

@Profile("test")
@RestController
@RequestMapping("/test")
public class TestController {
    private final ProductService productService;

    public TestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void createProduct(@RequestParam int count) {

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int i1 = random.nextInt(4);
            ProductDTO productDTO = new ProductDTO("laptop" + i, "Przenosny komputer", BigDecimal.valueOf(1500), CategoryType.values()[i1]);
            this.productService.createProduct(productDTO);
        }
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return this.productService.getProduct(id);
    }

    @DeleteMapping
    public boolean deleteAllProducts() {
        return this.productService.deleteAllProducts();
    }


}
