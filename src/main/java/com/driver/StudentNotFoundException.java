package com.driver;

public class StudentNotFoundException extends  RuntimeException{
    StudentNotFoundException(String str){
        super("student not found Exception");
    }
}
