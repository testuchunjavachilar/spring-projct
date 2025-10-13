package uz.salikhdev.springprojct.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/add") // /book/add
    public String getAddBookPage(){
        return "addBook";
    }

}
