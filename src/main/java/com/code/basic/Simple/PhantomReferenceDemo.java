package com.code.basic.Simple;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * Created by yankefei on 2022/3/15.
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) {
        Object obj = new Object();
        Object obj2 = new Object();
        ReferenceQueue<Object> softQueue = new ReferenceQueue<>();
        ReferenceQueue<Object> phantomQueue = new ReferenceQueue<>();
        SoftReference softReference = new SoftReference(obj,softQueue);
        PhantomReference<Object> phantomReference = new PhantomReference<>(obj2, phantomQueue);


    }
}
