package com.test.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Cache {
    private final JedisPool jedisPool;

    public Cache() throws IOException {
        Config config = new Config();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(config.getMaxIdle());
        jedisPoolConfig.setMaxTotal(config.getMaxTotal());
        jedisPool = new JedisPool(jedisPoolConfig, config.getRedisIp(), config.getRedisPort());
    }

    public List<Conference> cacheGetAllConferences() {
        List<Conference> conferenceList = new ArrayList<>();
        try (Jedis jedis = jedisPool.getResource()) {
            Map<String, String> ret = jedis.hgetAll(Consts.ALL_CONFERENCE_MAP);
            for (Map.Entry<String, String> entry : ret.entrySet()) {
                System.out.println("Restore bytes length:" + entry.getValue().getBytes(StandardCharsets.ISO_8859_1).length +
                        ", string length:" + entry.getValue().length());
                Conference conference = new Conference(Integer.parseInt(entry.getKey()));
                conference.deserialize(entry.getValue().getBytes(StandardCharsets.ISO_8859_1));
                conferenceList.add(conference);
            }
            return conferenceList;
        }
    }

    public boolean cacheAddConferences(List<Conference> conferences) {
        Map<String, String> fieldValuePair = new HashMap<>();
        for (Conference conference : conferences) {
            byte[] bytes = conference.serialize();
            String value = new String(bytes, StandardCharsets.ISO_8859_1);
            System.out.println("Save bytes length:" + bytes.length + ", string length:" + value.length());
            fieldValuePair.put(String.valueOf(conference.getId()), value);
        }
        try (Jedis jedis = jedisPool.getResource()) {
            long ret = jedis.hset(Consts.ALL_CONFERENCE_MAP, fieldValuePair);
            System.out.println("hset ret:" + ret);
        }
        return true;
    }

    public boolean cacheAddUsers(Conference conference, Map<Integer, User> users) {
        if (users.isEmpty()) {
            return false;
        }

        Map<String, String> usersMap = new HashMap<>();
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            byte[] bytes = entry.getValue().serializeObj();
            usersMap.put(String.valueOf(entry.getKey()), new String(bytes, StandardCharsets.ISO_8859_1));
        }

        try (Jedis jedis = jedisPool.getResource()) {
            final String key = String.format(Consts.CONFERENCE_USER_MAP, conference.getId());
            jedis.hset(key, usersMap);
        }

        return true;
    }

    public Map<Integer, User> cacheGetAllUsers(Conference conference) {
        Map<Integer, User> users = new HashMap<>();
        try (Jedis jedis = jedisPool.getResource()) {
            String key = String.format(Consts.CONFERENCE_USER_MAP, conference.getId());
            Map<String, String> elements = jedis.hgetAll(key);
            for (Map.Entry<String, String> entry : elements.entrySet()) {
                User user = User.deserializeObj(entry.getValue().getBytes(StandardCharsets.ISO_8859_1));
                users.put(Integer.parseInt(entry.getKey()), user);
            }
        }
        return users;
    }
}
