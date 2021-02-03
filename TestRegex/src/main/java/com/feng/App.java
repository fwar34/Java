package com.feng;

import java.util.regex.*;

public class App {
    public static void main(String[] args) {
        String regex1 = "20\\d\\d";
        System.out.println("2019".matches(regex1));
        System.out.println("2119".matches(regex1));

        //用\w可以匹配一个字母、数字或下划线，w的意思是word
        String regex2 = "java\\w";
        System.out.println("javac".matches(regex2));
        System.out.println("java8".matches(regex2));
        System.out.println("java_".matches(regex2));

        //用\d可以匹配一个数字，而\D则匹配一个非数字。
        String regex3 = "00\\D";
        System.out.println("001".matches(regex3));
        System.out.println("00a".matches(regex3));

        //修饰符?可以匹配0个或一个字符。
        String regex4 = "a\\d?";
        System.out.println("a".matches(regex4));
        System.out.println("a1".matches(regex4));
        System.out.println("a12".matches(regex4));

        //匹配一个7~8位数字的电话号码不能以0开头
        String regex5 = "[1-9]{7,8}";
        System.out.println("1234567".matches(regex5));
        System.out.println("12345678".matches(regex5));
        System.out.println("012345678".matches(regex5));

        //匹配非数字的3个字符
        String regex6 = "[^1-9]{3}";
        System.out.println("aBC".matches(regex6));
        System.out.println("aB3".matches(regex6));

        //或匹配
        String regex7 = "java|cpp|go";
        System.out.println("java".matches(regex7));
        System.out.println("cpp".matches(regex7));
        System.out.println("php".matches(regex7));

        //括号
        String regex8 = "learn\\s(java|php|[Gg]o)";
        System.out.println("learn java".matches(regex8));
        System.out.println("learn go".matches(regex8));
        System.out.println("learn Go".matches(regex8));

        //括号分组匹配
        //例如"区号-电话号"
        Pattern pattern = Pattern.compile("(\\d{3,4})\\-(\\d{6,8})");
        //Pattern pattern = Pattern.compile("(\\d{3,4})-(\\d{6,8})");
        Matcher matcher = pattern.matcher("010-12345678");
        if (matcher.matches()) {
            String g1 = matcher.group(1);
            String g2 = matcher.group(2);
            System.out.println(g1);
            System.out.println(g2);
        } else {
            System.out.println("match failed!");
        }

        //非贪婪匹配，给定一个匹配规则，加上?后就变成了非贪婪匹配。
        Pattern pattern1 = Pattern.compile("(\\d+?)(0*)");
        Matcher matcher1 = pattern1.matcher("123000");
        if (matcher1.matches()) {
            System.out.println(matcher1.group(1));
            System.out.println(matcher1.group(2));
        }

        //分割字符串
        "a b c".split("\\s");
        String[] str_array = "a, b :: c".split("[\\,\\s\\:]");
        System.out.println(str_array.length);
        for (String s : str_array) {
            System.out.println(s);
        }

        //搜索字符串
        String s = "the quick brown fox jumps over the lazy dog.";
        Pattern pattern2 = Pattern.compile("\\wo\\w");
        Matcher matcher2 = pattern2.matcher(s);
        //不需要调用matches()方法（因为匹配整个串肯定返回false），而是反复调用find()方法，
        // 在整个串中搜索能匹配上\\wo\\w规则的子串，并打印出来
        while (matcher2.find()) {
            String sub = s.substring(matcher2.start(), matcher2.end());
            System.out.println(sub);
        }

        //替换字符串
        String s2 = "The     quick\t\t brown   fox  jumps   over the  lazy dog.";
        String r = s2.replaceAll("\\s+", " ");
        System.out.println(r);

        //反向引用
        //例如：任何空格包裹的4字符单词的前后用<b>xxxx</b>括起来
        String r2 = s.replaceAll("\\s([a-z]{4})\\s", " <b>$1</b> ");
        System.out.println(r2);
    }
}
