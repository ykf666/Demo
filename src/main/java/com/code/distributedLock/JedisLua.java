package com.code.distributedLock;

import com.code.redis.JedisClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2021/1/25.
 */
public class JedisLua {

    private final static String REDIS_AUTH = "123456";

    public static void main(String[] args) {
        System.out.println("jedis客户端-->>setNX()实现分布式锁");
        JedisClient client = new JedisClient("127.0.0.1", 46379, REDIS_AUTH);
        String lockName = "lock";
        for (int i = 0; i < 20; i++) {
            String thread_name = "thread-" + i;
            Thread t = new Thread(() -> {
                while (true) {
                    try {
                        while (!client.lock(lockName, thread_name, 5)) {
                        }
                        System.out.println("加锁成功，线程名：" + thread_name);
                        //执行业务代码
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        break;
                    } finally {
                        if (client.unlock(lockName, thread_name)) {
                            System.out.println("unlock成功，线程名：" + thread_name);
                        } else {
                            System.out.println("unlock失败，线程名：" + thread_name);
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }, thread_name);
            t.start();
        }
    }
}
