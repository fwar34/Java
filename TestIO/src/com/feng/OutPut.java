package com.feng;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OutPut {
    public static void main(String[] args) throws IOException {
        writeFile1();
        writeFile2();
    }

    static void writeFile1() throws IOException {
        OutputStream output = new FileOutputStream("output.txt");
        output.write(72); // H
        output.write(101); // e
        output.write(108); // l
        output.write(108); // l
        output.write(111); // o
        output.close();
    }

    static void writeFile2() throws IOException {
        try (OutputStream output = new FileOutputStream("output.txt")) {
            output.write("writeFile2".getBytes(StandardCharsets.UTF_8));
        }
    }
}
