package com.company;

public class Person extends Object {
    protected final String test = "Test";
    protected String name;
    protected int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void eat() {
        System.out.println(this.name + " is eatting");
    }

    public String run() {
        return this.name + " is running";
    }

    public final void test() {

    }
}