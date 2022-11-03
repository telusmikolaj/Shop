package pl.waw.great.shop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.waw.great.shop.model.CartLineItem;
import pl.waw.great.shop.model.dto.CartLineItemDto;

@Mapper(componentModel = "spring")
public interface CartLineItemMapper {

    @Mapping(source = "cartLineItem", target = "productTitle", qualifiedByName = "getProductTitle")
    CartLineItemDto cartLineToDto(CartLineItem cartLineItem);

    @Named("getProductTitle")
    default String getProductTitle(CartLineItem cartLineItem) {
        return cartLineItem.getProduct().getTitle();
    }
}
