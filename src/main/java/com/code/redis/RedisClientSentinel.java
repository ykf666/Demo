package com.code.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by yankefei on 2021/1/18.
 */
public class RedisClientSentinel {

    private final static Logger log = LoggerFactory.getLogger(RedisClientSentinel.class);

    //连接实例的最大连接数
    private static int MAX_ACTIVE = 60;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 8;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT = 8000;
    //连接超时的时间　　
    private static int TIMEOUT = 8000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisSentinelPool jedisSentinelPool = null;
    //数据库模式是16个数据库 0~15
    public static final int DEFAULT_DATABASE = 0;

    public RedisClientSentinel(String masterName, Set<String> sentinels, String auth){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(MAX_ACTIVE);
        poolConfig.setMaxIdle(MAX_IDLE);
        poolConfig.setMaxWaitMillis(MAX_WAIT);
        poolConfig.setTestOnBorrow(TEST_ON_BORROW);
        jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, poolConfig);
    }

    public String getKey(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            log.error("redis异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public void setKey(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            log.error("redis异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String setKey(String key, String value, int expire, boolean nx) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            SetParams setParams = SetParams.setParams();
            if (expire > 0) {
                setParams.ex(expire);
            }
            //true=nx, false=xx
            if (nx) {
                setParams.nx();
            } else {
                setParams.xx();
            }
            return jedis.set(key, value, setParams);
        } catch (Exception e) {
            log.error("redis异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public List<String> getList(String key) {
        Jedis jedis = null;
        List<String> list = new ArrayList<>();
        try {
            jedis = jedisSentinelPool.getResource();
            long total = jedis.llen(key);
            if (total <= 0) {
                return list;
            }
            return jedis.lrange(key, 0, total - 1);
        } catch (Exception e) {
            log.error("redis异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return list;
    }

    public void clearList(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.ltrim(key, 1, 0);
        } catch (Exception e) {
            log.error("redis异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 写入任务队列消息
     *
     * @param key      list名称
     * @param message  消息内容
     * @param priority 优先级，0：普通，1：优先
     */
    public void listAdd(String key, String message, int priority) {
        Jedis jedis = null;
        try {
            if (StringUtils.isNotBlank(message)) {
                jedis = jedisSentinelPool.getResource();
                long index = 0;
                if (priority == 1) {
                    //插入队头
                    index = jedis.lpush(key, message);
                } else {
                    //加入队尾
                    index = jedis.rpush(key, message);
                }
                log.info("redis写入消息队列{}，index={}，content={}", key, index, message);
            } else {
                log.warn("message为空，redis未写入!");
            }
        } catch (Exception e) {
            log.error("redis写入异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取list长度
     *
     * @param key
     */
    public long lenList(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            return jedis.llen(key);
        } catch (Exception e) {
            log.error("redis写入异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    /**
     * 向hash表写入数据
     *
     * @param key
     * @param key
     * @param value
     */
    public void hashSet(String key, String field, String value) {
        Jedis jedis = null;
        try {
            if (StringUtils.isNotBlank(key)) {
                jedis = jedisSentinelPool.getResource();
                jedis.hset(key, field, value);
                //过期时间12小时
                jedis.expire(key, (12 * 60 * 60));
                log.info("redis写入hashKey={}，{}={}", key, field, value);
            } else {
                log.warn("hashKey为空，redis未写入!");
            }
        } catch (Exception e) {
            log.error("redis写入异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取哈希表特定字段值
     *
     * @param key
     * @param field
     */
    public String hashGet(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            long ttl = jedis.ttl(key);
            if (ttl == -1) {
                jedis.expire(key, (12 * 60 * 60));
            }
            String value = jedis.hget(key, field);
            return value;
        } catch (Exception e) {
            log.error("redis写入异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public void closejedisSentinelPool() {
        jedisSentinelPool.close();
    }
}
