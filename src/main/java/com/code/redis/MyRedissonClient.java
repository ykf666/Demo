package com.code.redis;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yankefei on 2021/1/19.
 */
public class MyRedissonClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyRedissonClient.class);

    private RedissonClient redissonClient = null;

    public MyRedissonClient(String ip, int port, String auth) {
        Config config = new Config();
        config.useSingleServer().setSslEnableEndpointIdentification(false).setAddress("redis://" + ip + ":" + port).setPassword(auth);
        redissonClient = Redisson.create(config);
    }

    public String getKey(String key) {
        if (redissonClient != null) {
            RBucket<String> keyObject = redissonClient.getBucket(key);
            return keyObject.get();
        } else {
            LOGGER.error("redisson客户端为空");
            return null;
        }
    }

    public void closeClient() {
        redissonClient.shutdown();
    }
}
