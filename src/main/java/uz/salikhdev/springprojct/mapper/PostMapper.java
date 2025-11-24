package uz.salikhdev.springprojct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.salikhdev.springprojct.dto.response.PostResponse;
import uz.salikhdev.springprojct.entity.post.Post;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "user.username", target = "author")
    @Mapping(target = "likes", expression = "java(likeCount(post))")
    PostResponse toResponse(Post post);

    List<PostResponse> toResponse(List<Post> posts);

    default long likeCount(Post post) {
        return post.getLikes() != null ? post.getLikes().size() : 0;
    }

}
