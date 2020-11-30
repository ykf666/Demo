package com.code.designPatterns.Sigleton;

/**
 * Created by yankefei on 2020/2/14.
 */
public class SingletonClass {

    public static SingletonClass INSTANCE = new SingletonClass();

    private volatile static SingletonClass instance;

    private SubSingletonClass a = new SubSingletonClass("aaa");
    private SubSingletonClass b;

    private SingletonClass() {
        b = new SubSingletonClass("bbb");
    }

    //double check locking
    public static SingletonClass getInstance() {
        if (instance == null) {
            synchronized (SingletonClass.class) {
                if (instance == null) {
                    instance = new SingletonClass();
                }
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "@" + Integer.toHexString(hashCode()) + ", {a=" + this.a.toString() + ", b=" + this.b.toString() + "}";
    }

}



