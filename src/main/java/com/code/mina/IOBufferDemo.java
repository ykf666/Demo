package com.code.mina;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * Created by yankefei on 2019/7/5.
 */
public class IOBufferDemo {

    public static void main(String[] args) {
        IoBuffer ioBuffer = IoBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            System.out.println("position=" + ioBuffer.position() + ", limit=" + ioBuffer.limit() + ", mark=" + ioBuffer.markValue() +
                    ", capacity=" + ioBuffer.capacity());
            ioBuffer.put((byte) i);
        }
    }

}
