
package com.kiet.Student.controller;

import com.kiet.Student.model.Student;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(
                "Rahul Sharma", 20, 1, "CSE", 2, 9.5));

        students.add(new Student(
                "Priya Verma", 19, 2, "IT", 1, 8.4));

        students.add(new Student(
                "Aman Kumar", 20, 3, "ECE", 2, 6.5));
    }

    // GET: View all students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    // POST: Add a student
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    // PUT: Update a student
    @PutMapping("/students/{id}")
    public Student updateStudent(
            @PathVariable int id,
            @RequestBody Student updatedStudent) {

        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(updatedStudent.getName());
                s.setAge(updatedStudent.getAge());
                return s;
            }
        }

        throw new RuntimeException("Student not found: " + id);
    }

    // DELETE: Remove a student
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        boolean removed = students.removeIf(s -> s.getId() == id);

        if (removed) {
            return "Student deleted successfully";
        }

        throw new RuntimeException("Student not found: " + id);
    }
}
