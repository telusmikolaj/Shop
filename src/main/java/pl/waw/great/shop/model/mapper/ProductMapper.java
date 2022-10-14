package pl.waw.great.shop.model.mapper;

import org.mapstruct.Mapper;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.model.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO productToDto(Product product);
    Product dtoToProduct(ProductDTO productDTO);
}
