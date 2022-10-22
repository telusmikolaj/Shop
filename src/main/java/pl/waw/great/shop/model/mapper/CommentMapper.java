package pl.waw.great.shop.model.mapper;

import org.mapstruct.Mapper;
import pl.waw.great.shop.model.Comment;
import pl.waw.great.shop.model.dto.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    CommentDto commentToDto(Comment comment);

    Comment dtoToComment(CommentDto commentDto);
}
