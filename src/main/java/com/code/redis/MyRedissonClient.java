package com.code.redis;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by yankefei on 2021/1/19.
 */
public class MyRedissonClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyRedissonClient.class);

    private RedissonClient redissonClient = null;

    public MyRedissonClient(String redisUrl, String auth) {
        Config config = new Config();
        //字符串操作需指定codec
        config.setCodec(StringCodec.INSTANCE).useSingleServer().setConnectionPoolSize(2)
                .setConnectionMinimumIdleSize(1)
                .setAddress(redisUrl)
                .setPassword(auth);
        redissonClient = Redisson.create(config);
    }

    public MyRedissonClient(String master, Set<String> slaves, String auth) {
        Config config = new Config();
        MasterSlaveServersConfig masterSlaveServersConfig =
                config.setCodec(StringCodec.INSTANCE)
                        .useMasterSlaveServers()
                        .setMasterConnectionPoolSize(1)
                        .setMasterConnectionMinimumIdleSize(1)
                        .setSlaveConnectionMinimumIdleSize(1)
                        .setSlaveConnectionPoolSize(1);
        masterSlaveServersConfig.setMasterAddress(master);
        for (String slave : slaves) {
            masterSlaveServersConfig.addSlaveAddress(slave);
        }
        masterSlaveServersConfig.setPassword(auth);
        redissonClient = Redisson.create(config);
    }

    public String getKey(String key) {
        if (redissonClient != null) {
            RBucket<String> keyObject = redissonClient.getBucket(key);
            if (keyObject.isExists()) {
                return keyObject.get();
            }
            LOGGER.warn("查询的key不存在");
        } else {
            LOGGER.error("redisson客户端为空");
        }
        return null;
    }

    public void setKey(String key, String value) {
        if (redissonClient != null) {
            RBucket<String> keyObject = redissonClient.getBucket(key);
            keyObject.set(value);
        } else {
            LOGGER.error("redisson客户端为空");
        }
    }

    public void closeClient() {
        redissonClient.shutdown();
    }
}
