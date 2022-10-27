package pl.waw.great.shop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.waw.great.shop.model.OrderLineItem;
import pl.waw.great.shop.model.dto.OrderLineDto;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {

    OrderLineItem dtoToOrderLine(OrderLineDto orderLineDto);
    @Mapping(source = "orderLineItem", target = "productTitle", qualifiedByName = "getProductTitle")
    OrderLineDto orderLineToDto(OrderLineItem orderLineItem);

    @Named("getProductTitle")
    default String getProductTitle(OrderLineItem orderLineItem) {
        return orderLineItem.getProduct().getTitle();
    }
}
