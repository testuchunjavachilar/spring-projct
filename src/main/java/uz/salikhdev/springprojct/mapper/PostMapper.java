package uz.salikhdev.springprojct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.salikhdev.springprojct.dto.response.PostResponse;
import uz.salikhdev.springprojct.entity.post.Post;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "user.username", target = "author")
    PostResponse toResponse(Post post);

    List<PostResponse> toResponse(List<Post> posts);
}
