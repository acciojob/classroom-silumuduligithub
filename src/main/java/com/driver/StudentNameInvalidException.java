package com.driver;

public class StudentNameInvalidException extends  RuntimeException{
    StudentNameInvalidException(String str){
        super("student not found Exception");
    }
}
