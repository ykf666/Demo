package com.code.demo.MultiThread;

/**
 * @author yan.kefei
 * @date 2018/5/16 16:31
 */
public class SyncClass implements Runnable {

    private static Integer count;

    public SyncClass() {
        count = 0;
    }

    @Override
    public void run() {
        synchronized (SyncClass.class) {
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
        Thread thread1 = new Thread(new SyncClass(), "thread-1");
        Thread thread2 = new Thread(new SyncClass(), "thread-2");
        thread1.start();
        thread2.start();
    }

}
