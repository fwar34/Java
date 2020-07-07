package com.feng;

public class Student implements Comparable<Student> {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student(" + name + ", " + score + ")";
    }


    @Override
    public int compareTo(Student o) {
        if (this.score < o.score) {
            return -1;
        }
        return 1;
    }
}
