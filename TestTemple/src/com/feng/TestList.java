package com.feng;

import org.omg.CORBA.INTERNAL;
import sun.security.util.math.intpoly.IntegerPolynomial;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class TestList {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("Apple");
        list1.add("Pear");
        list1.add("Orange");
        list1.add(null);
        System.out.println(list1.size());

        // java1.8没有of方法
        // List<Integer> list2 = List.of(1, 2, 3);

        for (int i = 0; i < list1.size(); ++i) {
            System.out.println(list1.get(i));
        }

        for (String s : list1) {
            System.out.println(s);
        }

        // toArray()方法直接返回一个Object[]数组,这种方法会丢失类型信息，所以实际应用很少。
        Object[] array = list1.toArray();
        for (Object s : array) {
            System.out.println(s);
        }

        // 第二种方式是给toArray(T[])传入一个类型相同的Array，List内部自动把元素复制到传入的Array中：
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        Integer[] array2 = list2.toArray(new Integer[3]);
        for (Integer i : array2) {
            System.out.println(i);
        }

        Number[] array3 = list2.toArray(new Number[3]);
        for (Number n : array3) {
            System.out.println(n);
        }
    }
}

class Student {
    private String name;
    private int age;
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
};
