package com.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.Throwable;

public class StudentService {
    private  StudentRepository studentRepository = new StudentRepository();
    public void addStudent(Student student) {
        studentRepository.add(student);
    }

    public void addTeacher(Teacher teacher) {
        studentRepository.add(teacher);
    }

    public void addStudentTeacherPair(String student, String teacher)throws  StudentNotFoundException, TeacherNotFoundException{
        Optional<Student> studentOptional = studentRepository.getStudent(student);
        Optional<Teacher> teacherOptional = studentRepository.getTeacher(teacher);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("invalid student name");
        }
        if(teacherOptional.isEmpty()){
            throw new TeacherNotFoundException("invalid teacher name");
        }
        Teacher teacherObject = teacherOptional.get();
        teacherObject.setNumberOfStudents(teacherObject.getNumberOfStudents()+1);
        studentRepository.add(teacherObject);
        studentRepository.add(student, teacher);
    }

    public Student getStudentByName(String name) throws StudentNotFoundException {
        Optional<Student> optionalStudent = studentRepository.getStudent(name);
        if(optionalStudent.isEmpty()) {
            throw new StudentNotFoundException("not found");
        }
        return optionalStudent.get();
    }

    public Teacher getTeacherByName(String name) throws StudentNotFoundException{
        Optional<Teacher> optionalTeacher = studentRepository.getTeacher(name);
        if(optionalTeacher.isEmpty()) {
            throw new StudentNotFoundException("not found");
        }
        return optionalTeacher.get();
    }

    public List<String> getStudentsByTeacherName(String teacher){
       return studentRepository.getStudentsByTeacherName(teacher);
    }

    public List<String> getAllStudents() {
        List<String> studentList = studentRepository.getAllStudents();
        return studentList;
    }

    public void deleteTeacherByName(String teacher){
      studentRepository.deleteTeacher(teacher);
       List<String> students = studentRepository.getStudentsByTeacherName(teacher);
       for(String student : students){
           studentRepository.deleteStudent(student);
       }
    }

    public void deleteAllTeachers() {
        ArrayList<String> teachers = studentRepository.getAllTeachers();
        for(String teacher : teachers){
            deleteTeacherByName(teacher);
        }
    }
}
