package uz.salikhdev.springprojct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.dto.TodoCreateDto;
import uz.salikhdev.springprojct.model.Status;
import uz.salikhdev.springprojct.model.Todo;
import uz.salikhdev.springprojct.repositroy.TodoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public void saveTodo(TodoCreateDto dto) {

        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title can not be empty");
        }

        if (dto.getDescription() == null || dto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description can not be empty");
        }

        Todo todo = Todo.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(Status.TODO)
                .createdAt(LocalDateTime.now())
                .build();

        todoRepository.save(todo);
    }

    public List<Todo> getAll() {
        return todoRepository.getAll();
    }

    public Todo getById(Long id) {
        Todo todo = todoRepository.getById(id);
        if (todo == null) {
            throw new RuntimeException("Todo not found ");
        }
        return todo;
    }

}
