package uz.salikhdev.springprojct.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.salikhdev.springprojct.service.StudentService;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }


    @DeleteMapping("/students/{id}")
    public String deleteStudent(Model model, @PathVariable("id") long id) {
        studentService.deleteStudent(id);
        return "index";
    }


}
