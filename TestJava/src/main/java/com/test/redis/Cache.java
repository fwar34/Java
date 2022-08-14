package com.test.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try (Jedis jedis = jedisPool.getResource()) {
            Map<String, String> ret = jedis.hgetAll(Consts.ALL_CONFERENCE_MAP);
        }
        return null;
    }

    public boolean cacheSaveConferences(List<Conference> conferences) {
        Map<String, String> fieldValuePair = new HashMap<>();
        for (Conference conference : conferences) {
            byte[] bytes = conference.serialize();
            fieldValuePair.put(String.valueOf(conference.getId()), bytes.toString());
        }
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(Consts.ALL_CONFERENCE_MAP, fieldValuePair);
        }
        return true;
    }
}
