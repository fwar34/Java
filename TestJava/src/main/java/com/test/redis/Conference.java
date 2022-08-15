package com.test.redis;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Conference implements Serializable {
    private String name;
    private int id;
    private String properties;

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
            outputStream.writeInt(id);
            outputStream.writeObject(name);
            if (!properties.isEmpty()) {
                outputStream.writeObject(properties);
            }
            return buffer.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deserialize(byte[] bytes) {
        ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(buffer))) {
            this.id = inputStream.readInt();
            this.name = (String) inputStream.readObject();
            this.properties = (String) inputStream.readObject();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
