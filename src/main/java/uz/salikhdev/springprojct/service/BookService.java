package uz.salikhdev.springprojct.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.exception.FiledNotCompletedException;
import uz.salikhdev.springprojct.model.Book;
import uz.salikhdev.springprojct.repository.BookRepository;

import java.io.FileNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void saveBook(Book book) {

        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new FiledNotCompletedException("Author can not be empty");
        }
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new FiledNotCompletedException("Title can not be empty");
        }
        if (book.getPrice() == null || book.getPrice() <= 0) {
            throw new FiledNotCompletedException("Price can not be empty");
        }
        if (book.getDescription() == null || book.getDescription().isEmpty()) {
            throw new FiledNotCompletedException("Description can not be empty");
        }

        bookRepository.saveBook(book);
        log.info("Book saved successfully");
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existById(id)) {
            throw new IllegalArgumentException("Book not found");
        }

        bookRepository.deleteBook(id);
    }



}
