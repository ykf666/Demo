package com.code.demo.MultiThread;

/**
 * @author yan.kefei
 * @date 2018/5/17 11:10
 */
public class MySingleton {

    //使用volatile关键字保其可见性
    volatile private static MySingleton instance = null;

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        try {
            //懒汉式
            if (instance == null) {
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(200);
                synchronized (MySingleton.class) {
                    if (instance == null) {
                        //二次检查
                        instance = new MySingleton();
                        /*
                         * 对象的创建有三个步骤：
                         * 1、memory = allocate;分配内存
                         * 2、init(memory);初始化对象
                         * 3、instance(memory);设置instance指向刚分配的内存地址
                         * 由于2和3没有数据依赖关系，根据happen-before原则，是可以重排序的
                         * 所以如果2、3顺序错乱，会导致一个线程访问instance不为null，但是对象未必已经初始化完成
                         * 因此使用volatile关键字保证多线程环境下安全
                         */

                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            MySingleton demo = MySingleton.getInstance();
            System.out.println(demo.hashCode());
        }
    }

}
