package com.code.concurrency.MultiThread;

public class ThreadLocalDemo {

    private ThreadLocal<Long> localLong = new ThreadLocal<Long>(){
        //ThreadLocal的初始化方法默认返回null，所以如果想要get某个值，需先set
        //或者可以重写initialValue方法
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    private ThreadLocal<String> localString = new ThreadLocal<>();

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
