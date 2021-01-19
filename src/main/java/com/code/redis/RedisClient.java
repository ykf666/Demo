package com.code.redis;

import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yankefei on 2021/1/18.
 */
public class RedisClient {

    private final static String REDIS_AUTH = "123456";

    public static void main(String[] args) {
        //jedis客户端
        JedisClient client = new JedisClient("127.0.0.1", 16379, REDIS_AUTH);
        System.out.println(client.getKey("a"));
        client.closeJedisPool();

        //jedisSentinel客户端
        Set<String> redisSentinels = new HashSet<>();
        redisSentinels.add(new HostAndPort("127.0.0.1", 26379).toString());
        redisSentinels.add(new HostAndPort("127.0.0.1", 26380).toString());
        redisSentinels.add(new HostAndPort("127.0.0.1", 26381).toString());
        JedisClientSentinel clientSentinel = new JedisClientSentinel("redis-master", redisSentinels, REDIS_AUTH);
        clientSentinel.setKey("b", "20");
        System.out.println(clientSentinel.getKey("b"));
        clientSentinel.closejedisSentinelPool();

        //redisson客户端
        MyRedissonClient myRedissonClient = new MyRedissonClient("127.0.0.1", 16379, REDIS_AUTH);
        System.out.println(myRedissonClient.getKey("a"));
        myRedissonClient.closeClient();
    }
}
