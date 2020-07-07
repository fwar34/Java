package com.feng;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Xiaoming", 20);
        Class clazz = person.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            PropertyDescriptor descriptor = null;
            try {
                descriptor = new PropertyDescriptor(key, clazz);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            Method method = null;
            if (descriptor != null) {
                method = descriptor.getReadMethod();
            }
            Object value = null;
            try {
                if (method != null) {
                    value = method.invoke(person);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println(key + ":" + value);
        }
    }
}
