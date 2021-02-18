package com.code.basic.Collections;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2021/2/4.
 */
public class DelayQueueDemo {

    public static void main(String[] args) {
        DelayQueue<Message> queue = new DelayQueue<>();
        for (int i = 0; i < 10; i++) {
            Message item = new Message(String.valueOf(i), i + 5000);
            queue.add(item);
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (queue.size() > 0) {
            Message item = queue.poll();
            if (item == null) {
                System.out.println("poll null");
            } else {
                System.out.println("poll " + item.getId() + ", delayTime=" + item.getDelay(TimeUnit.MILLISECONDS));
            }
        }
    }

    private static class Message implements Delayed {

        private String id;

        private long delayTime;

        public Message(String id, long delayTime) {
            this.id = id;
            this.delayTime = TimeUnit.MILLISECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.currentTimeMillis();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.delayTime - System.nanoTime(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            Message msg = (Message) o;
            return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1
                    : (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
        }

        public String getId() {
            return id;
        }
    }

}
