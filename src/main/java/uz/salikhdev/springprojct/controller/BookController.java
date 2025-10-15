package uz.salikhdev.springprojct.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.salikhdev.springprojct.model.Book;
import uz.salikhdev.springprojct.service.BookService;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String getBookPage(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book";
    }

    @GetMapping("/add")
    public String getAddBookPage() {
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("price") Double price,
            @RequestParam("description") String description
    ) {
        bookService.saveBook(new Book(title, author, price, description));
        return "redirect:/book";
    }

}
