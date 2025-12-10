package uz.salikhdev.springprojct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springprojct.entity.resource.Resource;
import uz.salikhdev.springprojct.entity.resource.ResourceStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Optional<Resource> findByKey(String key);

    List<Resource> findAllByStatus(ResourceStatus status);
}
