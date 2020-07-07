package com.company;

public class Student extends Person {
    public Student(String name, int age) {
        super(name, age);
    }

    @Override
    public String run() {
        return super.run() + "!";
    }
}
