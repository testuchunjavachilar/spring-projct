package uz.salikhdev.springprojct.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springprojct.maper.BookMapper;
import uz.salikhdev.springprojct.model.Book;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;


    public void saveBook(Book book) {
        try {
            String query = "INSERT INTO book(title, author, price, description) VALUES(?,?,?,?)";
            jdbcTemplate.update(query, book.getTitle(), book.getAuthor(), book.getPrice(), book.getDescription());
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
        }
    }

    public void deleteBook(Long id) {
        try {
            String sql = "DELETE FROM book WHERE ID=?";
            jdbcTemplate.update(sql, id);

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
        }
    }

    public Book getBookById(Long id) {
        String sql = "SELECT * FROM book WHERE ID=?";
        return jdbcTemplate.queryForObject(sql, new BookMapper());
    }

    public List<Book> getAllBooks() {
        try {
            String sql = "SELECT * FROM book;";
            return jdbcTemplate.query(sql, new BookMapper());

        }catch (Exception e){
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }


}
