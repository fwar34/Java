package com.company;
import java.util.List;
import java.util.Iterator;


public class Main {

    public static void main(String[] args) {
	// write your code here
        List<String> list = List.of("apple", "pear", "banana");
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            String s = it.next();
            System.out.println(s);
        }

        for (String s : list) {
            System.out.println(s);
        }

        //List Array转换
        List<Integer> nlist = List.of(12, 34, 56);
        Integer[] array = nlist.toArray(new Integer[3]);
        for (Integer i : array) {
            System.out.println(i);
        }

        Number[] narray = nlist.toArray(new Number[3]);
        for (Number n : narray) {
            System.out.println(n);
        }

        Integer[] array2 = nlist.toArray(Integer[]::new);
        for (Integer n : array2) {
            System.out.println(n);
        }

        Integer[] array3 = {1, 2, 3};
        List<Integer> list2 = List.of(array3);
        for (Integer i : list2) {
            System.out.println(i);
        }
   }
}
