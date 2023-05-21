package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController {

    private StudentService studentService = new StudentService();
    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
       studentService.addStudent(student);
        return new ResponseEntity<>("New student added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher){
       studentService.addTeacher(teacher);
       return new ResponseEntity<>("New teacher added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/add-student-teacher-pair")
    public ResponseEntity<String> addStudentTeacherPair(@RequestParam String student, @RequestParam String teacher) {
        try {
                studentService.addStudentTeacherPair(student, teacher);
            return new ResponseEntity<>("New student-teacher pair added successfully", HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>("either student not found or teacher not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get-student-by-name/{name}")
    public ResponseEntity<String> getStudentByName(@PathVariable String name){
        try {
            Student student = studentService.getStudentByName(name);
            return new ResponseEntity<>(name, HttpStatus.OK);
        }catch (StudentNotFoundException e){
            return new ResponseEntity<>("student not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-teacher-by-name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name) {
        try {
            Teacher teacher = studentService.getTeacherByName(name);
            return new ResponseEntity<>(teacher, HttpStatus.CREATED);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get-students-by-teacher-name/{teacher}")
    public ResponseEntity<List<String>> getStudentsByTeacherName(@PathVariable String teacher){
            try {
                List<String> studentList = studentService.getStudentsByTeacherName(teacher);
                return new ResponseEntity<>(studentList, HttpStatus.CREATED);
            } catch (StudentNotFoundException e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    @GetMapping("/get-all-students")
    public ResponseEntity<List<String>> getAllStudents(){
        List<String> students = studentService.getAllStudents(); // Assign list of student by calling service layer method
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-teacher-by-name")
    public ResponseEntity<String> deleteTeacherByName(@RequestParam String teacher) {
        try {
           studentService.deleteTeacherByName(teacher);
            return new ResponseEntity<>(teacher + " removed successfully", HttpStatus.CREATED);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>("teacher not found exception", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete-all-teachers")
    public ResponseEntity<String> deleteAllTeachers(){
       studentService.deleteAllTeachers();
        return new ResponseEntity<>("All teachers deleted successfully", HttpStatus.CREATED);
    }
}
