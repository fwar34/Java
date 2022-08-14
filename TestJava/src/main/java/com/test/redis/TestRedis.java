package com.test.redis;

import redis.clients.jedis.Jedis;

public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.125.13", 6379);

    }
}
