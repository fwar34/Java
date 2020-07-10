package com.company;

import java.util.Objects;

public class Person {
    private String firstName;
    private String secondName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
//        int h = 0;
//        h = 31 * h + firstName.hashCode();
//        h = 31 * h + secondName.hashCode();
//        h = 31 * h + age;
//        return h;
        return Objects.hash(firstName, secondName, age);
    }

    @Override
    public boolean equals(Object obj) {
       if (obj instanceof Person) {
           Person p = (Person)obj;
           return Objects.equals(this.firstName, p.firstName) &&
                   Objects.equals(this.secondName, p.secondName) &&
                   this.age == p.age;
       }
       return false;
    }
}
