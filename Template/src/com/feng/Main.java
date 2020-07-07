package com.feng;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> str_list = new ArrayList<>();
        str_list.add("abc");
        str_list.add("def");
        String one = str_list.get(0);
        System.out.println(one);

        String[] strs = { "Apple", "Pear", "Orange" };
        Arrays.sort(strs);
        System.out.println(Arrays.toString(strs));

        Student[] students = { new Student("XiaoMing", 59), new Student("Xiaohong", 60) };
        Arrays.sort(students);
        System.out.println(Arrays.toString(students));

        Pair<String> p = new Pair<>("Xiao", "Ming");
        String first = p.getFirst();
        String second = p.getSecond();
        System.out.println(p.getClass() == Pair.class);

        Pair<Integer> integ = Pair.create(1, 3);
        Integer ifirst = integ.getFirst();
        Integer isecond = integ.getSecond();
        System.out.println(integ.getClass() == Pair.class);
    }
}
