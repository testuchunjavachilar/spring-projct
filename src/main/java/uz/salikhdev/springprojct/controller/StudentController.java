package uz.salikhdev.springprojct.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.salikhdev.springprojct.model.Student;
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

    @GetMapping("/students/add")
    public String addStudentPage(Model model,
                                 @RequestParam(required = false) String message,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String age,
                                 @RequestParam(required = false) String email) {
        model.addAttribute("message", message);
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("email", email);
        return "addStudent";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam String name, @RequestParam int age, @RequestParam String email) {

        try {
            studentService.addStudent(name, age, email);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/students/add?message=Student already exists";
        }
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(Model model, @PathVariable("id") long id) {
        studentService.deleteStudent(id);
        return "index";
    }

    @GetMapping("/students/update/{id}")
    public String updateStudent(@PathVariable("id") long id) {
        Student student = studentService.getStudent(id);
        return "redirect:/students/add?name=" + student.getName() + "&age=" + student.getAge() + "&email=" + student.getEmail();
    }

    @PutMapping("/students/update/{id}")
    public String updateStudent(@PathVariable long id, @RequestParam String name, @RequestParam int age, @RequestParam String email) {
        studentService.updateStudent(id, name, age, email);
        return "redirect:/";
    }

}
