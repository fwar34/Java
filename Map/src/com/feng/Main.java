package com.feng;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("Xiaoming", 20), new Person("Xiaohuang", 22));
        Map<String, Person> map = new TreeMap<>();
//        Map<String, Person> map = new HashMap<>();
        for (Person person : persons) {
            map.put(person.getName(), person);
        }

        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }
    }
}
