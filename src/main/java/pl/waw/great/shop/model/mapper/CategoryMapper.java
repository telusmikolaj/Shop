package pl.waw.great.shop.model.mapper;

import org.mapstruct.Mapper;
import pl.waw.great.shop.model.Category;
import pl.waw.great.shop.model.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto commentToDto(Category category);
}
