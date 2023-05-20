package com.driver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Teacher{

    private String name;

    private int numberOfStudents;
    private int age;
    public Teacher() {
    }

    public Teacher(String name, int numberOfStudents, int age) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
        this.age = age;
    }
}