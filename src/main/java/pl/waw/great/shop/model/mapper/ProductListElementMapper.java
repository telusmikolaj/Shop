package pl.waw.great.shop.model.mapper;

import org.mapstruct.Mapper;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.model.dto.ProductListElementDto;

@Mapper(componentModel = "spring")
public interface ProductListElementMapper {
    ProductListElementDto productToDto(Product product);
}
