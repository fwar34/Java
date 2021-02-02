package com.feng;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        System.out.println(list.get(0) + " " + list.get(1));

        TestCompare();

        TestExtends();
    }

    static void TestCompare() {
        String[] ss = new String[] {"Orange", "Apple", "Pear"};
        Arrays.sort(ss);
        System.out.println(Arrays.toString(ss));

        Person[] ps = new Person[] {
                new Person("Bob", 61),
                new Person("Alice", 88),
                new Person("Lily", 75),
        };
        Arrays.sort(ps);
        System.out.println(Arrays.toString(ps));
    }

    static void TestExtends() {
        Pair<Integer> p = new Pair<>(123, 456);
        int n = add(p);
        System.out.println(n);
    }

    static int add(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number second = p.getSecond();
        return first.intValue() + second.intValue();
    }
}

// Exception in thread "main" java.lang.ClassCastException: com.feng.Person cannot be cast to java.lang.Comparable
// class Person {
class Person implements Comparable<Person> {
    private String name;
    private int score;
    public Person(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.name + "," + this.score;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
}

class Pair<T> {
    private T first;
    private T second;
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }
}
