package com.test.redis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    private final String redisIp;
    private final int redisPort;
    private final int maxTotal;

    public int getMaxTotal() {
        return maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    private final int maxIdle;

    public Config() throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get("redis.properties")));
        redisIp = properties.getProperty("redis.ip");
        redisPort = Integer.parseInt(properties.getProperty("redis.port"));
        maxTotal = Integer.parseInt(properties.getProperty("redis.maxTotal"));
        maxIdle = Integer.parseInt(properties.getProperty("redis.maxIdle"));
    }

    public String getRedisIp() {
        return redisIp;
    }

    public int getRedisPort() {
        return redisPort;
    }
}
