package com.code.basic.jvm;

import java.util.concurrent.TimeUnit;

/**
 * 对象的状态分为三种：
 * 1、可达：从GC ROOTS开始，可以到达该对象
 * 2、可复活的：对象的所有引用被释放，但对象可能在finalize()方法中复活
 * 3、不可达：对象的finalize()方法被调用，并且对象没有复活，则可以认为是不可达；
 *      不可达的对象不能再被复活，因为finalize方法只会被调用一次
 *
 * 如果对象到GC Roots没有引用链，则第一次标记进行筛选，判断对象是否有必要执行finalize方法;
 * 1、如果没有重写finalize方法，或者finalize方法被调用过，则认为对象不可达，可以被垃圾回收
 * 2、如果finalize方法被重写过，且还未执行过，则对象会被插入到一个队列中，由一个虚拟机自动创建、优先级低的Finalizer线程触发finalize方法执行，会对队列中对象第二次标记
 * 3、如果在执行finalize方法时，对象重新建立了引用关联，则会被移除"即将回收"集合，只有对象会再次出现没有引用链的情况，此时finalize方法不会再执行，因为该方法只会被调用一次
 * Created by yankefei on 2023/8/23.
 */
public class FinalizeDemo {

    //类静态变量，属于GC Roots
    public static FinalizeDemo obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类重写的finalize()方法");
        obj = this;
        System.out.println("obj=this，当前对象引用重新赋值");
    }

    public static void main(String[] args) {
        obj = new FinalizeDemo();
        obj = null;

        System.out.println("调用System.gc()，第1次GC");
        System.gc();
        //由于Finalizer线程优先级较低，所以等待2s
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (obj == null) {
            System.out.println("obj is dead");
        } else {
            System.out.println("obj is alive");
        }

        System.out.println("调用System.gc()，第2次GC");
        obj = null;
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (obj == null) {
            System.out.println("obj is dead");
        } else {
            System.out.println("obj is alive");
        }
    }

}
