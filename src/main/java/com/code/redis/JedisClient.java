package com.code.redis;

import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yankefei on 2021/1/18.
 */
public class JedisClient {

    public static void main(String[] args) {
        RedisClient client = new RedisClient("127.0.0.1", 16379, "123456");
        System.out.println(client.getKey("a"));
        Set<String> redisSentinels = new HashSet<>();
        redisSentinels.add(new HostAndPort("127.0.0.1", 26379).toString());
        redisSentinels.add(new HostAndPort("127.0.0.1", 26380).toString());
        redisSentinels.add(new HostAndPort("127.0.0.1", 26381).toString());
        RedisClientSentinel clientSentinel = new RedisClientSentinel("redis-master", redisSentinels, "123456");
        clientSentinel.setKey("b", "20");
        System.out.println(clientSentinel.getKey("b"));
    }
}
