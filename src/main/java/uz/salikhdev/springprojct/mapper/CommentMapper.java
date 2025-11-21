package uz.salikhdev.springprojct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.salikhdev.springprojct.dto.response.CommentResponse;
import uz.salikhdev.springprojct.entity.comment.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    @Mapping(target = "authorUsername", source = "author.username")
    CommentResponse toResponse(Comment comment);

    List<CommentResponse> toResponse(List<Comment> comments);

}
