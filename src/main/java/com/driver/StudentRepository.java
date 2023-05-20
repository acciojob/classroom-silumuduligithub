package com.driver;

import java.util.*;

import static org.springframework.util.ClassUtils.isPresent;

public class StudentRepository {
    private Map<String, Student> studentMap = new HashMap<>();
   private Map<String, Teacher> teacherMap = new HashMap<>();
    private List<List<String>> teachersStudentPair = new ArrayList<>();
    public void addStudent(Student student) {
        studentMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }

    public Optional<String> addstudentTeacher(String student, String teacher) {
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            List<String> pair = new ArrayList<>();
            pair.add(student);
            pair.add(teacher);
            teachersStudentPair.add(pair);
           return Optional.of(student);
        }
        return Optional.empty();
    }

    public Optional<Student> getStudentByName(String name) {
        for(Map.Entry<String, Student>entry : studentMap.entrySet()){
            if(entry.getKey().equals(name)){
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    public Optional<Teacher> getTeacherByName(String name) {
        for(Map.Entry<String, Teacher>entry : teacherMap.entrySet()){
            if(entry.getKey().equals(name)){
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    public List<String> getStudentsByTeacherName(String teacher) {
       List<String> ans = new ArrayList<>();
        for(List<String> pair : teachersStudentPair){
           String master = pair.get(1);
           if(master.equals(teacher)){
               ans.add(pair.get(0));
           }
       }
       return ans;
    }

    public List<String> getAllStudents() {
        List<String> ans = new ArrayList<>();
        for(String student : studentMap.keySet()){
            ans.add(student);
        }
        return ans;
    }

    public Optional<String> deleteTeacherByName(String teacher) {
        for(Map.Entry<String, Teacher>entry : teacherMap.entrySet()) {
            if (entry.getKey().equals(teacher)) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }

    public void deleteAllTeachers() {
        teacherMap = null;
        teachersStudentPair = null;
    }
}