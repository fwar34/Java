package com.test.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application {
    private static final Cache cache;

    static {
        try {
            cache = new Cache();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<Conference> conferenceList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Conference conference = new Conference(110 + i, "11" + i);
            conference.setProperties("properties_xxx_11" + i);
            conferenceList.add(conference);
        }

        if (!cache.cacheAddConferences(conferenceList)) {
            System.out.println("add conferences failed!");
        }

        conferenceList.clear();
        conferenceList = cache.cacheGetAllConferences();
        for (Conference conference : conferenceList) {
            System.out.printf("Conference id:%d, name:%s, properties:%s %n", conference.getId(), conference.getName(), conference.getProperties());
            for (int j = 0; j < 5; j++) {
                User user = new User(j + 1, "name_" + (j + 1));
                conference.addUser(user);
            }

            if (!cache.cacheAddUsers(conference, conference.getUsers())) {
                System.out.printf("conference:%d add users failed!%n", conference.getId());
            }

            Map<Integer, User> users = cache.cacheGetAllUsers(conference);
            for (Map.Entry<Integer, User> entry : users.entrySet()) {
                System.out.printf("user:%d name:%s%n", entry.getKey(), entry.getValue().getName());
            }
        }
    }
}
