package com.test.threadlocal;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class Main {

}

class TestThreadLocal {
    private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();
}

class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;
}
