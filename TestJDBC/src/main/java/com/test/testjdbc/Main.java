package com.test.testjdbc;

import javax.xml.transform.Result;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String jdbc_url = "jdbc:mysql://192.168.169.2:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
        String jdbc_user = "root";
        String jdbc_password = "111111";
        try (Connection connection = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_password)) {
            // 使用Java对数据库进行操作时，必须使用PreparedStatement，严禁任何通过参数拼字符串的代码！，这里只是测试例子
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select id, grade, name, gender FROM students WHERE gender=1")) {
                    while (resultSet.next()) {
                        long id = resultSet.getLong(1);
                        long grade = resultSet.getLong(2);
                        String name = resultSet.getString(3);
                        int gender = resultSet.getInt(4);
                        System.out.println(id + " " + grade + " " + name + " " + gender);
                    }
                }
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("select id, grade, name, gender FROM students WHERE gender = ? AND grade = ?")) {
                preparedStatement.setObject(1, 0);
                preparedStatement.setObject(2, 3);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        long id = resultSet.getLong(1);
                        long grade = resultSet.getLong(2);
                        String name = resultSet.getString(3);
                        int gender = resultSet.getInt(4);
                        System.out.println(id + " " + grade + " " + name + " " + gender);
                    }
                }
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into students (id, grade, name, gender, score) VALUES (?, ?, ?, ?, ?)")) {
                preparedStatement.setObject(1, 999);
                preparedStatement.setObject(2, 1);
                preparedStatement.setObject(3, "Bob");
                preparedStatement.setObject(4, 1);
                preparedStatement.setObject(5, 90);
                int n = preparedStatement.executeUpdate();
                System.out.println(n);
            }
        }
    }
}
