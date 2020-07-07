package com.company;

public class Main {

    public static void main(String[] args) {
        Person p = new Person("XiaoMing", 20);
        System.out.println(p.run());

        Person ps = new Student("XiaoHuang", 21);
        System.out.println(ps.run());
    }
}
