package pl.waw.great.shop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.waw.great.shop.model.Order;
import pl.waw.great.shop.model.dto.OrderDto;

@Mapper(componentModel = "spring", uses = OrderLineMapper.class)
public interface OrderMapper {

    @Mapping(source = "order", target = "userName", qualifiedByName = "getUserName")
    OrderDto orderToDto(Order order);

    Order dtoToOrder(OrderDto orderDto);

    @Named("getUserName")
    default String getUserName(Order order) {
        return order.getUser().getName();
    }
}
