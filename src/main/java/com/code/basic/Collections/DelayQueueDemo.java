package com.code.basic.Collections;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.*;

/**
 * Created by yankefei on 2021/2/4.
 */
public class DelayQueueDemo {

    private final static DelayQueue<Message> queue = new DelayQueue<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int delay = RandomUtils.nextInt(1, 10);
                Message item = new Message(String.valueOf(delay), delay);
                boolean offerResult = queue.offer(item);
                if (offerResult) {
                    System.out.println("offer " + item.getId());
                }

            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            Thread t = new ReadThread(String.valueOf(i));
            executorService.execute(t);
        }
        executorService.shutdown();
    }

    private static class Message implements Delayed {

        private String id;

        //单位：秒
        private long delayTime;

        public Message(String id, long delayTime) {
            this.id = id;
            this.delayTime = TimeUnit.MILLISECONDS.convert(delayTime, TimeUnit.SECONDS) + System.currentTimeMillis();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return "{" +
                    "id='" + id + '\'' +
                    ", delayTime=" + this.getDelay(TimeUnit.MILLISECONDS) +
                    '}';
        }
    }

    private static class ReadThread extends Thread {

        public ReadThread(String threadName) {
            this.setName(threadName);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    long startTime = System.currentTimeMillis();
                    Message message = queue.take();
                    long endTime = System.currentTimeMillis();
                    System.out.println(Thread.currentThread().getName() + " take success, time=" + (endTime - startTime) + "ms, message=" + message.toString());
                } catch (InterruptedException e) {

                }
            }
        }
    }

}
