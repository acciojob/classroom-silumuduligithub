package com.driver;

public class TeacherNotFoundException extends  RuntimeException{
    TeacherNotFoundException(String str) {
        super("teacher not found exception");
    }
}
