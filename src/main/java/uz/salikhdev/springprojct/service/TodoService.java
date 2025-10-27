package uz.salikhdev.springprojct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.dto.TodoCreateDto;
import uz.salikhdev.springprojct.dto.TodoListDto;
import uz.salikhdev.springprojct.entity.Status;
import uz.salikhdev.springprojct.entity.Todo;
import uz.salikhdev.springprojct.exception.NotFoundException;
import uz.salikhdev.springprojct.repositroy.TodoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<TodoListDto> getAll() {
        List<Todo> all = todoRepository.findAll();
        List<TodoListDto> dtos = new ArrayList<>();

        for (Todo todo : all) {

            TodoListDto d = TodoListDto.builder()
                    .id(todo.getId())
                    .title(todo.getTitle())
                    .build();

            dtos.add(d);

        }

        return dtos;
    }

    public Todo getById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));
    }

    public void updateStatus(Long id, String status) {
        Todo todo = getById(id);
        todo.setStatus(Status.fromString(status));
        todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {

        if (!todoRepository.existsById(id)) {
            throw new NotFoundException("Already deleted");
        }

        todoRepository.deleteById(id);
    }

    public List<TodoListDto> getByStatus(String status) {

        List<Todo> all = todoRepository.findAllByStatus(Status.fromString(status));
        List<TodoListDto> dtos = new ArrayList<>();

        for (Todo todo : all) {
            TodoListDto d = TodoListDto.builder()
                    .id(todo.getId())
                    .title(todo.getTitle())
                    .build();

            dtos.add(d);
        }

        return dtos;
    }

}
