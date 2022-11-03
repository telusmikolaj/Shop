package pl.waw.great.shop.model.mapper;

import org.mapstruct.Mapper;
import pl.waw.great.shop.model.Message;
import pl.waw.great.shop.model.dto.MessageDto;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message messageDtoToMessage(MessageDto messageDto);
    MessageDto messageToMessageDto(Message message);
}
