// https://www.liaoxuefeng.com/wiki/1252599548343744/1265117217944672
package com.company;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 123);
        map.put("pear", 456);
        map.put("banana", 789);
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println(key + " = " + value);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " = " + value);
        }

        Person xiaoming = new Person();
        xiaoming.setFirstName("Xiao");
        xiaoming.setSecondName("ming");
        xiaoming.setAge(15);

        Map<Person, Integer> person_scores = new HashMap<>();
        person_scores.put(xiaoming, 90);
        for (Map.Entry<Person, Integer> entry : person_scores.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}