package uz.salikhdev.springprojct.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.salikhdev.springprojct.entity.resource.Resource;
import uz.salikhdev.springprojct.entity.resource.ResourceStatus;
import uz.salikhdev.springprojct.repository.ResourceRepository;
import uz.salikhdev.springprojct.service.MinioService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GlobalScheduler {

    private final ResourceRepository resourceRepository;
    private final MinioService minioService;


    @Scheduled(fixedDelay = 20_000 , initialDelay = 20_000) // Har 1 daqiqada bir marta ishga tushadi
    private void resourceDeleteScheduler() {
        List<Resource> allPassives = resourceRepository.findAllByStatus(ResourceStatus.PASSIVE);

        for (Resource resource : allPassives) {
            minioService.delete(resource.getKey());
            resource.setStatus(ResourceStatus.DELETED);
            log.warn("Resource is deleted: " + resource.getKey());
            resourceRepository.save(resource);
        }
    }


}
