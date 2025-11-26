package uz.salikhdev.springprojct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.salikhdev.springprojct.dto.response.ProfileResponse;
import uz.salikhdev.springprojct.entity.user.User;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "firstName", source = "firstname")
    @Mapping(target = "lastName", source = "lastname")
    ProfileResponse toDto(User user);

}
