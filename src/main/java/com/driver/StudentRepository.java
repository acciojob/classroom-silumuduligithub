package com.driver;

import java.util.*;
public class StudentRepository {
    private Map<String, Student> studentMap = new HashMap<>();
   private Map<String, Teacher> teacherMap = new HashMap<>();
    private Map<String, ArrayList<String>> teacherStudentMap = new HashMap<>();
    public void add(Student student) {
        studentMap.put(student.getName(), student);
    }

    public void add(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }

    public void add(String student, String teacher) {
        ArrayList<String> students = teacherStudentMap.getOrDefault(student, new ArrayList<>());
        students.add(student);
        teacherStudentMap.put(teacher, students);
    }

    public Optional<Student> getStudent(String name) {
        if(studentMap.containsKey(name)){
            return Optional.of(studentMap.get(name));
        }
        return Optional.empty();
    }

    public Optional<Teacher> getTeacher(String name) {
       if(teacherMap.containsKey(name)){
           return Optional.of(teacherMap.get(name));
       }
       return Optional.empty();
    }

    public List<String> getStudentsByTeacherName(String teacher) {
      return teacherStudentMap.getOrDefault(teacher, new ArrayList<>());
    }

    public List<String> getAllStudents() {
       return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher) {
        teacherMap.remove(teacher);
        teacherStudentMap.remove(teacher);
    }


    public void deleteStudent(String student) {
        studentMap.remove(student);
    }

    public ArrayList<String> getAllTeachers() {
        return new ArrayList<>(teacherMap.keySet());
    }
}
