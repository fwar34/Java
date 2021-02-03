package com.feng;
public class Factorial {
    public static long fact(long n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        long r = 1;
        for (long i = 1; i <= n; ++i) {
            r *= i;
        }
        return r;
    }
}
