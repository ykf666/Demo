package com.code.demo.Unit.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.AfterClass;

/**
 * Junit的生命周期
 * @author yankefei
 *
 */
public class LifeCycleTest {

	/**
	 * 每个测试用例都会重新创建当前的Class实例，可以看到Constructor执行了两次。
	 */
	public LifeCycleTest() {
		super();
		System.out.println("<<Junit Constructor Method>>");
	}

	@BeforeClass
	public static void beforeClassM() {
		System.out.println("<<Before Class>>");
	}

	@Before
	public void beforeM() {
		System.out.println("<<Before>>");
	}

	@AfterClass
	public static void afterClassM() {
		System.out.println("<<After Class>>");
	}

	@After
	public void after() {
		System.out.println("<<After>>");
	}

	@Test
	public void testMethod1() {
		System.out.println("Test Method 1.");
	}

	@Test
	public void testMethod2() {
		System.out.println("Test Method 2.");
	}
}
