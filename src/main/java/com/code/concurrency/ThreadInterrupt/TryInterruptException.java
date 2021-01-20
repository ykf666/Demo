package com.code.concurrency.ThreadInterrupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by yankefei on 2021/1/20.
 */
public class TryInterruptException {

    public static void main(String[] args) {
        SubThread thread = new SubThread();
        thread.start();
        LockSupport.parkNanos(12000000000l);
        thread.interrupt();
    }

    private static class SubThread extends Thread {

        private final static Logger LOGGER = LoggerFactory.getLogger(SubThread.class);

        @Override
        public void run() {
            LOGGER.info("业务线程开始运行");
            long start = System.currentTimeMillis();
            while (true) {
                try {
                    for (int i = 0; i < 10; i++) {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            LOGGER.info("运行中，忽略中断");
                            this.interrupt();
                        }
                        LOGGER.info("线程运行--> {}", i);
                        if (this.isInterrupted()) {
                            //主要逻辑运行结束，判断是否中断，退出
                            LOGGER.info("线程已中断-1，抛出中断异常");
                            throw new InterruptedException("interrupted exception");
                        }
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            LOGGER.info("线程已中断-2，抛出中断异常");
                            throw e;
                        }
                    }
                } catch (InterruptedException e) {
                    LOGGER.info("捕获到内循环中断异常{}", e.getMessage());
                    break;
                }
            }
            long end = System.currentTimeMillis();
            LOGGER.info("业务线程结束，运行时间：{}ms", (end - start));
        }
    }
}
