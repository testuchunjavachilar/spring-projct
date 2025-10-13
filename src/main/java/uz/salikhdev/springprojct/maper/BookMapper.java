package uz.salikhdev.springprojct.maper;

import org.springframework.jdbc.core.RowMapper;
import uz.salikhdev.springprojct.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        Double price = rs.getDouble("price");
        String description = rs.getString("description");
        Timestamp timestamp = rs.getTimestamp("create_at");

        LocalDateTime createAt = timestamp != null
                ? timestamp.toLocalDateTime()
                : null; // yoki LocalDateTime.now()

        return new Book(id, title, author, price, description, createAt);
    }
}
