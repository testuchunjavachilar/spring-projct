package uz.salikhdev.springprojct.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentService {

    private final AtomicInteger id = new AtomicInteger(0);
    private List<Student> students;

    @PostConstruct
    private void init() {
        students = new ArrayList<>();
        students.add(new Student(id.getAndIncrement(), "Salikh", 20, "salikhdev@gmail.com"));
        students.add(new Student(id.getAndIncrement(), "Murat", 21, "murat@gmail.com"));
        students.add(new Student(id.getAndIncrement(), "Ali", 22, "ali@gmail.com"));
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudent(long id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new RuntimeException("Student not found");
    }

    public void  deleteStudent(long id){
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                return;
            }
        }
    }


}
