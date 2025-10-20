package uz.salikhdev.springprojct.mapper;

import org.springframework.jdbc.core.RowMapper;
import uz.salikhdev.springprojct.model.Status;
import uz.salikhdev.springprojct.model.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TodoMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        String status = rs.getString("status");
        Timestamp createAt = rs.getTimestamp("created_at");

        return Todo.builder()
                .id(id)
                .title(title)
                .description(description)
                .status(Status.fromString(status))
                .createdAt(createAt.toLocalDateTime())
                .build();
    }
}
