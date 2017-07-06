package com.code.demo.unit.testng;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.annotations.Test;

public class MultiThreadTest {
	
	public MultiThreadTest() {
		System.out.println("construct...");
	}

	private static AtomicInteger sum = new AtomicInteger(1);

	private static int b = 0;

	/**
	 * @threadPoolSize-->表示执行的线程池的大小
	 * @invocationCount-->表示执行的次数
	 * @timeOut-->超时时间(毫秒)
	 */
	@Test(threadPoolSize = 3, invocationCount = 10, timeOut = 1000)
	public void testNGTestThread() {
		System.out.println("[ " + this.getcurrentThreadId() + " ]........"
				+ sum.getAndIncrement() + ".........." + (b++));
	}

	public long getcurrentThreadId() {
		return Thread.currentThread().getId();
	}

}
