// https://www.liaoxuefeng.com/wiki/1252599548343744/1298364142452770
// Filter模式（或者装饰器模式：Decorator）
package com.feng;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Filter {
    public static void main(String[] args) throws IOException {
        byte[] data = "hello".getBytes(StandardCharsets.UTF_8);
        try (CountInputStream input = new CountInputStream(new ByteArrayInputStream(data))) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char) n);
            }
            System.out.println("Total read " + input.getBytesRead() + " bytes.");
        }
    }
}

class CountInputStream extends FilterInputStream {
    private int count = 0;

    CountInputStream(InputStream input) {
        super(input);
    }

    public int getBytesRead() {
        return this.count;
    }

    public int read() throws IOException {
        int n = in.read();
        if (n != -1) {
            this.count++;
        }
        return n;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int n = in.read(b, off, len);
        if (n != -1) {
            this.count++;
        }
        return n;
    }
}