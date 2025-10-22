package uz.salikhdev.springprojct.repositroy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springprojct.mapper.TodoMapper;
import uz.salikhdev.springprojct.model.Status;
import uz.salikhdev.springprojct.model.Todo;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Todo todo) {
        try {
            String query = "INSERT INTO todo (title,description,status) VALUES (?,?,?)";
            jdbcTemplate.update(query, todo.getTitle(), todo.getDescription(), todo.getStatus().toString());
        } catch (Exception e) {
            log.error("Xatolik: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Todo getById(Long id) {
        try {
            String query = "SELECT * FROM todo WHERE id = ?";
            Object[] args = {id};
            return jdbcTemplate.queryForObject(query, args, new TodoMapper());
        } catch (Exception e) {
            log.error("Xatolik: {}", e.getMessage());
            return null;
        }
    }

    public List<Todo> getAll() {
        try {
            String query = "SELECT * FROM todo";
            return jdbcTemplate.query(query, new TodoMapper());
        } catch (Exception e) {
            log.error("Xatolik: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateStatus(Status newStatus, Long id) {
        try {
            String query = "UPDATE todo SET status = ? WHERE id=?";
            Object[] args = {newStatus.name(), id};
            jdbcTemplate.update(query, args);
        } catch (Exception e) {
            log.error("Xatolik: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deteleTodo(Long id) {
        try {
            String query = "DELETE FROM todo WHERE id=?";
            Object[] args = {id};
            jdbcTemplate.update(query, args);
        } catch (Exception e) {
            log.error("Xatolik: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean existById(Long id){
        String query = "SELECT EXISTS (SELECT 1 FROM todo WHERE id = ?)";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(query, args, Boolean.class);
    }

}
