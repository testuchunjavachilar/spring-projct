package uz.salikhdev.springprojct.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.salikhdev.springprojct.dto.MessageDto;
import uz.salikhdev.springprojct.dto.TodoCreateDto;
import uz.salikhdev.springprojct.entity.Todo;
import uz.salikhdev.springprojct.service.TodoService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Tag(name = "Todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    @Operation(summary = "barcha todolarni olish")
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String status
    ) {

        if (status == null) {
            return ResponseEntity.ok(todoService.getAll());
        } else {
            return ResponseEntity.ok(todoService.getByStatus(status));
        }
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
                MessageDto.builder()
                        .message("Todo success created")
                        .status(true)
                        .timestamp(LocalDateTime.now().toString())
                        .build()
        );
    }


    @PostMapping("/todos/status/{id}")
    public ResponseEntity<MessageDto> updateStatus(@RequestParam("status") String status, @PathVariable Long id) {
        todoService.updateStatus(id, status);
        return ResponseEntity.ok(
                MessageDto.builder()
                        .message("Status success updated")
                        .status(true)
                        .timestamp(LocalDateTime.now().toString())
                        .build()
        );
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                MessageDto.builder()
                        .message("Todo success deleted")
                        .status(true)
                        .timestamp(LocalDateTime.now().toString())
                        .build()
        );
    }

}
