package uz.salikhdev.springprojct.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.salikhdev.springprojct.exception.FiledNotCompletedException;

@ControllerAdvice
public class ErrorHandleController {


    @ExceptionHandler(value = {FiledNotCompletedException.class})
    public String handleException(Exception exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "addBook";
    }


}
