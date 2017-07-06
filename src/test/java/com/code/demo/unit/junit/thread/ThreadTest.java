package com.code.demo.unit.junit.thread;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

//@RunWith(MultiThreadedRunner.class)
@RunWith(ConcurrentTestRunner.class)
public class ThreadTest {

	@Test
	@ThreadCount(10)
	public void testMethod(){
		System.out.println("[ "+Thread.currentThread().getId()+" ]...");
	}
	
}
