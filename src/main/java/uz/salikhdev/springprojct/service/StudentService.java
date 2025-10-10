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

    public void deleteStudent(long id) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                return;
            }
        }
    }

    public void addStudent(String name, int age, String email) {

        students.stream()
                .filter(student -> student.getEmail().equals(email))
                .findFirst()
                .ifPresent(student -> {
                    throw new RuntimeException("Email already exists");
                });

        Student student = Student.builder()
                .id(id.getAndIncrement())
                .age(age)
                .name(name)
                .email(email)
                .build();

        students.add(student);
    }

    public void updateStudent(long id, String name, int age, String email) {

        Student student = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setEmail(email);

        students.set(students.indexOf(student), student);
    }


}
