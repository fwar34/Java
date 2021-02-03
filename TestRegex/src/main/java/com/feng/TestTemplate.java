package com.feng;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestTemplate {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "feng");
        map.put("lang", "java");
        System.out.println(render(map));
    }

    public static String render(Map<String, String> map) {
        String template = "Hello, ${name}! You are learning ${lang}!";
        Pattern pattern = Pattern.compile("\\$\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(template);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, map.get(matcher.group(1)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
