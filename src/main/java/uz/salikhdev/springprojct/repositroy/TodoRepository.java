package uz.salikhdev.springprojct.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springprojct.entity.Status;
import uz.salikhdev.springprojct.entity.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByStatus(Status status);

}
