package com.test.redis;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Conference implements Serializable {
    private String name;
    private int id;

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Conference(int id) {
        this.id = id;
        users = new HashMap<>();
    }

    public Conference(int id, String name) {
        this.id = id;
        this.name = name;
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    private Map<Integer, User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] serialize() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(buffer)) {
            outputStream.writeObject(this);
            return buffer.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
