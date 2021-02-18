package com.code.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yankefei on 2021/1/18.
 */
public class RedisClient {

    private final static String REDIS_AUTH = "123456";

    public static void main(String[] args) {
        //jedis客户端（单机模式）
        System.out.println("jedis客户端-->>单机模式");
        JedisClient client = new JedisClient("127.0.0.1", 46379, REDIS_AUTH);
        client.setKey("a", "100");
        System.out.println(client.getKey("a"));
        client.closeJedisPool();

        //jedis客户端（哨兵模式）
        System.out.println("jedis客户端-->>哨兵模式");
        Set<String> redisSentinels = new HashSet<>();
        redisSentinels.add(new HostAndPort("127.0.0.1", 26379).toString());
        redisSentinels.add(new HostAndPort("127.0.0.1", 26380).toString());
        redisSentinels.add(new HostAndPort("127.0.0.1", 26381).toString());
        JedisClientSentinel clientSentinel = new JedisClientSentinel("redis-master", redisSentinels, REDIS_AUTH);
        clientSentinel.setKey("b", "20");
        System.out.println(clientSentinel.getKey("b"));
        clientSentinel.closejedisSentinelPool();

        //redisson客户端(单机模式)
        System.out.println("redisson客户端-->>单机模式");
        MyRedissonClient myRedissonClient = new MyRedissonClient("redis://127.0.0.1:16379", REDIS_AUTH);
        myRedissonClient.setKey("c", "200");
        System.out.println(myRedissonClient.getKey("c"));
        myRedissonClient.closeClient();

        //redisson客户端(主从模式)
        System.out.println("redisson客户端-->>主从模式");
        String masterUrl = "redis://127.0.0.1:16379";
        Set<String> slaves = new HashSet<>();
        slaves.add("redis://127.0.0.1:16380");
        slaves.add("redis://127.0.0.1:16381");
        MyRedissonClient myRedissonClient2 = new MyRedissonClient(masterUrl, slaves, REDIS_AUTH);
        System.out.println(myRedissonClient2.getKey("c"));
        myRedissonClient2.closeClient();

        //redisson客户端（sentinel模式）
        System.out.println("redisson客户端-->>哨兵模式");
        Set<String> sentinels = new HashSet<>();
        sentinels.add("redis://127.0.0.1:26379");
        sentinels.add("redis://127.0.0.1:26380");
        sentinels.add("redis://127.0.0.1:26381");
        MyRedissonClient sentinelClient = new MyRedissonClient(sentinels, "redis-master", REDIS_AUTH);
        System.out.println(sentinelClient.getKey("c"));
        sentinelClient.closeClient();

        //redisson客户端（集群模式）
        System.out.println("redisson客户端-->>集群模式");
        Set<String> nodes = new HashSet<>();
        nodes.add("redis://127.0.0.1:36061");
        nodes.add("redis://127.0.0.1:36062");
        nodes.add("redis://127.0.0.1:36063");
        nodes.add("redis://127.0.0.1:36064");
        nodes.add("redis://127.0.0.1:36065");
        nodes.add("redis://127.0.0.1:36066");
        MyRedissonClient clusterClient = new MyRedissonClient(nodes, null);
        clusterClient.setKey("d", "300");
        clusterClient.closeClient();
    }
}
