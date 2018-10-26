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
                    if (instance == null) {//二次检查
                        instance = new MySingleton();
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
