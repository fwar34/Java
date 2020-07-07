package com.feng;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Xiaoming", 20));
        persons.add(new Person("Xiaohuang", 23));
        System.out.println(persons);
        System.out.println(persons.contains(new Person("Xiaoming", 20)));
    }
}
