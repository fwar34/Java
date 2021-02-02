package com.feng;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        readFile1();
        readFile2();
        readFile3();
        readFile4();
        readFile5();
        readFile6();
    }

    static void readFile1() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("input.txt");
            int n;
            // 反复调用read()方法，直到返回-1
            while ((n = inputStream.read()) != -1) {
                System.out.println(n);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    // 实际上，编译器并不会特别地为InputStream加上自动关闭。编译器只看try(resource = ...)中的对象是否实现了
    // java.lang.AutoCloseable接口，如果实现了，就自动加上finally语句并调用close()方法。InputStream
    // 和OutputStream都实现了这个接口，因此，都可以用在try(resource)中
    static void readFile2() throws IOException {
        try (InputStream input = new FileInputStream("input.txt")) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println(n);
            }
        } // 编译器在此自动为我们写入finally并调用close()
    }

    static void readFile3() throws IOException {
        try (InputStream inputStream = new FileInputStream("input.txt")) {
            byte[] buffer = new byte[1000];
            int n;
            while ((n = inputStream.read(buffer)) != -1) {
                System.out.println("read " + n + " bytes.");
            }
        }
    }

    // 用FileInputStream可以从文件获取输入流，这是InputStream常用的一个实现类。
    // 此外，ByteArrayInputStream可以在内存中模拟一个InputStream
    static void readFile4() throws IOException {
        byte[] data = { 72, 101, 108, 108, 111, 33 };
        try (InputStream input = new ByteArrayInputStream(data)) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char)n);
            }
        }
    }

    // ByteArrayInputStream实际上是把一个byte[]数组在内存中变成一个InputStream，
    // 虽然实际应用不多，但测试的时候，可以用它来构造一个InputStream。
    static void readFile5() throws IOException {
        String s;
        try (InputStream input = new FileInputStream("input.txt")) {
            int n;
            StringBuilder builder = new StringBuilder();
            while ((n = input.read()) != -1) {
                builder.append((char) n);
            }

            System.out.println(builder.toString());
        }
    }

    // 要测试上面的程序，就真的需要在本地硬盘上放一个真实的文本文件。如果我们把代码稍微改造一下，提取一个readAsString()的方法
    static String readAsString(InputStream input) throws IOException {
        int n;
        StringBuilder builder = new StringBuilder();
        while ((n = input.read()) != -1) {
            builder.append((char) n);
        }
        return builder.toString();
    }
    // 对这个String readAsString(InputStream input)方法进行测试就相当简单，因为不一定要传入一个真的FileInputStream：
    static void readFile6() throws IOException {
        byte[] data = {72, 101, 108, 108, 111, 33};
        InputStream input = new ByteArrayInputStream(data);
        System.out.println(readAsString(input));
    }
}

