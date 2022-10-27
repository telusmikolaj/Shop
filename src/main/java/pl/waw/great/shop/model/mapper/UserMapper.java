package pl.waw.great.shop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.waw.great.shop.model.User;
import pl.waw.great.shop.model.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToUser(UserDto userDto);
    UserDto userToDto(User user);
}
