package pl.waw.great.shop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.waw.great.shop.model.Cart;
import pl.waw.great.shop.model.dto.CartDto;

@Mapper(componentModel = "spring", uses = CartLineItemMapper.class)
public interface CartMapper {

    @Mapping(source = "cart", target = "userName", qualifiedByName = "getUserName")
    CartDto cartToDto(Cart cart);

    @Named("getUserName")
    default String getUserName(Cart cart) {

        return cart.getUser().getName();
    }
}