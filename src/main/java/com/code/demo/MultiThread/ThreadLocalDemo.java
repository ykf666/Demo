package com.code.demo.MultiThread;

public class ThreadLocalDemo {

    private ThreadLocal<Long> localLong = new ThreadLocal<Long>();

    private ThreadLocal<String> localString = new ThreadLocal<String>();

    public void set(){
        localLong.set(Thread.currentThread().getId());
        localString.set(Thread.currentThread().getName());
    }

    public Long getLong(){
        return localLong.get();
    }

    public String getString(){
        return localString.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo demo = new ThreadLocalDemo();

        demo.set();
        System.out.println(demo.getLong());
        System.out.println(demo.getString());


        Thread t = new Thread(){
            @Override
            public void run() {
                demo.set();
                System.out.println(demo.getLong());
                System.out.println(demo.getString());
            }
        };
        t.start();
        t.join();

        System.out.println(demo.getLong());
        System.out.println(demo.getString());
    }
}
