package com.code.concurrency.MultiThread;

/**
 * 一个线程访问一个对象中的synchronized(this)同步代码块时，其他试图访问该对象的线程将被阻塞
 * @author yan.kefei
 * @date 2017/11/1 13:51
 */
public class SyncObject implements Runnable {
    private static int count;

    public SyncObject() {
        count = 0;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
//        SyncObject syncThread = new SyncObject();
//        Thread thread1 = new Thread(syncThread, "thread-1");
//        Thread thread2 = new Thread(syncThread, "thread-2");
        Thread thread1 = new Thread(new SyncObject(), "thread-1");
        Thread thread2 = new Thread(new SyncObject(), "thread-2");
        thread1.start();
        thread2.start();
    }

}
