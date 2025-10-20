package uz.salikhdev.springprojct.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.salikhdev.springprojct.dto.MessageDto;
import uz.salikhdev.springprojct.dto.TodoCreateDto;
import uz.salikhdev.springprojct.model.Todo;
import uz.salikhdev.springprojct.service.TodoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAll() {
        return ResponseEntity.ok(todoService.getAll());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long id) {
        Todo todo = todoService.getById(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping("/todos")
    public ResponseEntity<MessageDto> createTodo(@RequestParam String title, @RequestParam String description) {
        todoService.saveTodo(new TodoCreateDto(title, description));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                MessageDto
                        .builder()
                        .message("Todo success created")
                        .status(true)
                        .timestamp(LocalDateTime.now().toString())
                        .build()
        );
    }


    @PostMapping("/todos/status")
    public ResponseEntity<?> updateStatus() {
        return null;
    }


}
