package uz.salikhdev.springprojct.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.salikhdev.springprojct.entity.resource.Resource;
import uz.salikhdev.springprojct.entity.resource.ResourceStatus;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.excetion.NotFoundException;
import uz.salikhdev.springprojct.repository.ResourceRepository;
import uz.salikhdev.springprojct.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final UserRepository userRepository;
    private final MinioService minioService;
    private final ResourceRepository resourceRepository;

    @Transactional
    public void uploadUserAvatar(Long id, MultipartFile file) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getImageUrl() != null && !user.getImageUrl().isBlank()) {
            Resource resource = resourceRepository.findByKey(user.getImageUrl())
                    .orElseThrow(() -> new NotFoundException("Resource not found"));
            resource.setStatus(ResourceStatus.PASSIVE);
            resource.setPassiveAt(LocalDateTime.now());
            resourceRepository.save(resource);
        }


        Map<String, String> map = minioService.upload(file, "avatars");

        Resource resource = Resource.builder()
                .originalName(file.getOriginalFilename())
                .size(Long.valueOf(map.get("size")))
                .contentType(file.getContentType())
                .key(map.get("fileName"))
                .status(ResourceStatus.ACTIVE)
                .build();

        user.setImageUrl(map.get("fileName"));

        userRepository.save(user);
        resourceRepository.save(resource);
        }
}
