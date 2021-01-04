package com.code.concurrency.MultiThread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yankefei on 2018/11/21.
 */
public class CopyOnWriteListDemo {
    /**
     * 应用场景：读多写少的多线程环境，因为内部实现写操作加锁，新copy一个数组执行add操作，然后将新数组指向cowList，而读操作不加锁
     * 缺点：内存使用问题，因为是copy数组，所以会占用一倍内存
     *       数据一致性问题，只能保证最终一致性，如果想要写的内容，很快就被读到，建议不适用COW
     */
    public static void main(String[] args) {
        List<String> list = Arrays.asList(new String[]{"tom", "lucy", "jack", "666"});
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<String>(list);

        for (int i = 1; i <= 100; i++) {
            new Thread("thread-" + i) {
                @Override
                public void run() {
                    int count = 1;
                    while (true) {
                        cowList.add(Thread.currentThread().getName() + " " + count++);
                    }
                }
            }.start();
        }

        cowList.forEach(item -> {
            System.out.print(cowList.hashCode() + " ");
            System.out.println(item);
        });
    }

}
