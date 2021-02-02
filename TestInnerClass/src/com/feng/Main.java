package com.feng;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Outer outer = new Outer("feng");
        Outer.Inner inner = outer.new Inner();
        inner.hello();
        outer.asyncHello();

        // 除了接口外，匿名类也完全可以继承自普通类
        // map1是一个普通的HashMap实例，但map2是一个匿名类实例，只是该匿名类继承自HashMap。
        // map3也是一个继承自HashMap的匿名类实例，并且添加了static代码块来初始化数据。
        // 观察编译输出可发现Main$1.class和Main$2.class两个匿名类文件。
        HashMap<String, String> m1 = new HashMap<>();
        HashMap<String, String> m2 = new HashMap<String, String>() {};
        HashMap<String, String> m3 = new HashMap<String, String>() {
            {
                put("A", "1");
                put("B", "2");
            }
        };
        System.out.println(m3.get("A"));
    }
}

class Outer {
    private String name;
    Outer(String name) {
        this.name = name;
    }

    // Outer是一个普通类，而Inner是一个Inner Class，它与普通类有个最大的不同，就是
    // Inner Class的实例不能单独存在，必须依附于一个Outer Class的实例。
    class Inner {
        void hello() {
            // Inner Class和普通Class相比，除了能引用Outer实例外，还有一个额外的“特权”，
            // 就是可以修改Outer Class的private字段，因为Inner Class的作用域在Outer Class内部，
            // 所以能访问Outer Class的private字段和方法。
            System.out.println("hello " + Outer.this.name);
        }
    }

    // 观察asyncHello()方法，我们在方法内部实例化了一个Runnable。Runnable本身是接口，接口是不能实例化的，
    // 所以这里实际上是定义了一个实现了Runnable接口的匿名类，并且通过new实例化该匿名类，
    // 然后转型为Runnable。在定义匿名类的时候就必须实例化它
    void asyncHello() {
        // 匿名类和Inner Class一样，可以访问Outer Class的private字段和方法。之所以我们要定义匿名类，
        // 是因为在这里我们通常不关心类名，比直接定义Inner Class可以少写很多代码。
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello " + Outer.this.name);
            }
        };
        new Thread(r).start();
    }
}