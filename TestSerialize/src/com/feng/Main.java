package com.feng;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException, Exception {
        Test1();
        Test2();
    }

    private static void Test1() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        // ObjectOutputStream既可以写入基本类型，如int，boolean，也可以写入String（以UTF-8编码），
        // 还可以写入实现了Serializable接口的Object。
        // 因为写入Object时需要大量的类型信息，所以写入的内容很大。
        try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            output.writeInt(12345);
            output.writeUTF("Hello");
            output.writeObject(Double.valueOf(123.456));
        }
        System.out.println(Arrays.toString(buffer.toByteArray()));

        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer.toByteArray());
        try (ObjectInputStream input = new ObjectInputStream(inputStream)) {
            int n = input.readInt();
            String s = input.readUTF();
            Double d = (Double) input.readObject();
            System.out.println(d);
        }
    }

    private static void Test2() throws IOException, Exception {
        SerializeUser();
        User user = DeserialzieUser();
        if (user != null) {
            System.out.println(user.getName());
        }
    }

    private static void SerializeUser() throws FileNotFoundException, IOException {
        User user = new User();
        user.setAge(1);
        user.setName("feng");
        user.setSex("man");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("object.txt")));
        outputStream.writeObject(user);
        System.out.println("User serialize success");
        outputStream.close();
    }

    private static User DeserialzieUser() throws Exception, IOException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("object.txt")));
        User user = (User)inputStream.readObject();
        System.out.println("User deserialize success");
        return user;
    }
}

class User implements Serializable {
    private static final long serialVersionUID = 3604972003323896788L;
    private transient int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String sex;
}