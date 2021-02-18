package com.code.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yankefei on 2021/1/18.
 */
public class JedisClient {

    private final static Logger log = LoggerFactory.getLogger(JedisClient.class);

    //连接实例的最大连接数
    private static int MAX_ACTIVE = 60;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 8;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT = 8000;
    //连接超时的时间　　
    private static int TIMEOUT = 2000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;
    //数据库模式是16个数据库 0~15
    public static final int DEFAULT_DATABASE = 0;

    private final static String UNLOCK_SCRIPT = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    public JedisClient(String host, int port, String auth) {
        //初始化Redis连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(MAX_ACTIVE);
        jedisPoolConfig.setMaxIdle(MAX_IDLE);
        jedisPoolConfig.setMaxWaitMillis(MAX_WAIT);
        jedisPoolConfig.setTestOnBorrow(TEST_ON_BORROW);
        jedisPool = new JedisPool(jedisPoolConfig, host, port, TIMEOUT, auth, DEFAULT_DATABASE);
    }

    public String getKey(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
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
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            log.error("redis异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @param key
     * @param value
     * @param expire
     * @param nx/xx，nx:只有当key不存在时操作，xx:只有当key存在时操作
     * @return
     */
    public String setKey(String key, String value, int expire, boolean nx) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            SetParams setParams = SetParams.setParams();
            if (expire > 0) {
                //ex，过期时间单位，秒
                //px，过期时间单位，毫秒
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
            jedis = jedisPool.getResource();
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
            jedis = jedisPool.getResource();
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
                jedis = jedisPool.getResource();
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
            jedis = jedisPool.getResource();
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
                jedis = jedisPool.getResource();
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
            jedis = jedisPool.getResource();
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

    public void closeJedisPool() {
        jedisPool.close();
    }

    //过期时间单位秒
    public boolean lock(String lockName, String value, int expire) {
        String result = this.setKey(lockName, value, expire, true);
        if ("OK".equals(result)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean unlock(String key, String param) {
        Jedis jedis = null;
        try {
            List<String> keys = new ArrayList<>();
            List<String> params = new ArrayList<>();
            keys.add(key);
            params.add(param);
            jedis = jedisPool.getResource();
            Object object = jedis.eval(UNLOCK_SCRIPT, keys, params);
            if ((long) object == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("redis执行脚本异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public void flushDB(int dbNo) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbNo);
            long count = jedis.dbSize();
            System.out.println("当前数据库存在key数量为：" + count);
            ScanParams params = new ScanParams();
            params.count(20);
            String cursor = "0";
            ScanResult<String> scanResult = jedis.scan(cursor, params);
            cursor = scanResult.getCursor();
            while (!"0".equals(cursor)) {
                List<String> list = scanResult.getResult();
                System.out.println("获取到keys数：" + list.size());
                for (String key : list) {
                    Long ttl = jedis.ttl(key);
                    if (ttl == -1) {
                        jedis.del(key);
                        System.out.println("删除key: " + key);
                    }
                }
                scanResult = jedis.scan(cursor, params);
                cursor = scanResult.getCursor();
            }
            List<String> list2 = scanResult.getResult();
            System.out.println("获取到keys数：" + list2.size());
            for (String key : list2) {
                Long ttl = jedis.ttl(key);
                if (ttl == -1) {
                    jedis.del(key);
                    System.out.println("删除key: " + key);
                }
            }
        } catch (Exception e) {
            log.error("redis执行脚本异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
