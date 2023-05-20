package com.driver;

import java.util.List;
import java.util.Optional;

public class StudentService {
    private  StudentRepository studentRepository = new StudentRepository();
    public String addStudent(Student student) {
        studentRepository.addStudent(student);
        return "student added successfully";
    }

    public String addTeacher(Teacher teacher) {
        studentRepository.addTeacher(teacher);
        return "teacher added successfully";
    }

    public String addStudentTeacherPair(String student, String teacher)throws  StudentNotFoundException{
       Optional<String>stringOptional = studentRepository.addstudentTeacher(student, teacher);
       if(stringOptional.isEmpty()){
           throw new StudentNotFoundException("not found");
       }
       return "both pair are added successfully";
    }

    public Student getStudentByName(String name) throws StudentNotFoundException {
        Optional<Student> optionalStudent = studentRepository.getStudentByName(name);
        if(optionalStudent.isEmpty()) {
            throw new StudentNotFoundException("not found");
        }
        return optionalStudent.get();
    }

    public Teacher getTeacherByName(String name) throws StudentNotFoundException{
        Optional<Teacher> optionalTeacher = studentRepository.getTeacherByName(name);
        if(optionalTeacher.isEmpty()) {
            throw new StudentNotFoundException("not found");
        }
        return optionalTeacher.get();
    }

    public List<String> getStudentsByTeacherName(String teacher) throws StudentNotFoundException{
        List<String> studentByTeacherList = studentRepository.getStudentsByTeacherName(teacher);
        if(studentByTeacherList.size() == 0){
            throw new StudentNotFoundException("not found");
        }
        return studentByTeacherList;
    }

    public List<String> getAllStudents() {
        List<String> studentList = studentRepository.getAllStudents();
        return studentList;
    }

    public String deleteTeacherByName(String teacher) throws StudentNotFoundException{
       Optional<String> stringOptional = studentRepository.deleteTeacherByName(teacher);
       if(stringOptional.isEmpty()){
           throw new StudentNotFoundException("not found");
       }
        return "teacher deleted successfully";
    }

    public String deleteAllTeachers() {
        studentRepository.deleteAllTeachers();
        return "all teacher are deleted";
    }
}
