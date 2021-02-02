/*
https://www.liaoxuefeng.com/wiki/1252599548343744/1260466215676512
最佳实践:
如果不确定是否需要public，就不声明为public，即尽可能少地暴露对外的字段和方法。
把方法定义为package权限有助于测试，因为测试类和被测试类只要位于同一个package，测试代码就可以访问被测试类的package权限方法。
一个.java文件只能包含一个public类，但可以包含多个非public类。如果有public类，文件名必须和public类的名字相同。

小结:
Java内建的访问权限包括public、protected、private和package权限；
Java在方法内部定义的变量是局部变量，局部变量的作用域从变量声明开始，到一个块结束；
final修饰符不是访问权限，它可以修饰class、field和method；
一个.java文件只能包含一个public类，但可以包含多个非public类。
* */
package com.company;

public class Main {
    // 用final修饰method可以阻止被子类覆写：
    protected final void f() {
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.age = 10;
        person.g();
        System.out.println("age is: " + person.age);
    }
}

class Person {
    int age;
    void g() {
        System.out.println("My name is feng.");
    }
}
