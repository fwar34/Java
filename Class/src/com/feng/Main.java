package com.feng;

public class Main {
    public static void main(String[] args) {
//        Class clazz = Test.class; // 不会初始化静态块
//        System.out.println(Test.name);
        try {
            Class clazz = Class.forName("com.feng.Test"); // 会初始化
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Test test = new Derived();
        if (test instanceof Derived) {
            // 这里可以向下转换了
            System.out.println("ok");
        } else {
            System.out.println("not zok");
        }

        Test test2 = new Derived();
        System.out.println(test.getClass() == Derived.class);
        System.out.println(test.getClass() == test2.getClass());
    }
}

class Test {
    public static String name = "feng";
    static {
        System.out.println("Test静态块");
    }

    public Test() {
        System.out.println("Test构造");
    }
}

class Derived extends Test { }
