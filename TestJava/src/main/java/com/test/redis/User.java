package com.test.redis;

import java.io.*;

public class User implements Serializable {
    private String name;
    private int id;

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
            outputStream.writeInt(this.id);
            outputStream.writeObject(this.name);
            return buffer.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deserialize(byte[] bytes) {
        ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(buffer)) {
            id = objectInputStream.readInt();
            name = (String) objectInputStream.readObject();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte[] serializeObj() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(buffer);
            objectOutputStream.writeObject(this);
            return buffer.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User deserializeObj(byte[] bytes) {
        ByteArrayInputStream buffer = new ByteArrayInputStream(bytes);
        try (ObjectInputStream inputStream = new ObjectInputStream(buffer)) {
            return (User) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
