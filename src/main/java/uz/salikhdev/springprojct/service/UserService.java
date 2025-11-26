package uz.salikhdev.springprojct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.dto.request.ProfileEditDto;
import uz.salikhdev.springprojct.dto.response.ProfileResponse;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.entity.user.UserRole;
import uz.salikhdev.springprojct.excetion.InvalidTokenException;
import uz.salikhdev.springprojct.excetion.NotFoundException;
import uz.salikhdev.springprojct.mapper.ProfileMapper;
import uz.salikhdev.springprojct.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    public ProfileResponse getProfile(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!user.getIsActive()) {
            throw new InvalidTokenException("User not active");
        }

        return profileMapper.toDto(user);
    }

    public void updateProfile(Long id, ProfileEditDto dto, User authUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (authUser.getRole().equals(UserRole.USER) && !authUser.getId().equals(id)) {
            throw new InvalidTokenException("You don't have permission to update this profile");
        }

        if (!user.getIsActive()) {
            throw new InvalidTokenException("User not active");
        }

        if (dto.firstName() != null && !dto.firstName().isBlank()) {
            user.setFirstname(dto.firstName());
        }

        if (dto.lastName() != null && !dto.lastName().isBlank()) {
            user.setLastname(dto.lastName());
        }

        if (dto.bio() != null && !dto.bio().isBlank()) {
            user.setBio(dto.bio());
        }

        /*if (authUser.getRole().equals(UserRole.USER) && authUser.getId().equals(id)) {
            userRepository.save(user);
        } else if (authUser.getRole().equals(UserRole.ADMIN)) {
            userRepository.save(user);
        } else {
            throw new InvalidTokenException("You don't have permission to update this profile");
        }*/
        userRepository.save(user);
    }

    public void statusUpdate(Long id, Boolean active) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setIsActive(active);
        userRepository.save(user);
    }
}
