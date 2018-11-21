package com.code.demo.Unit.testng;

import org.testng.annotations.*;

/**
 * testNG的生命周期
 * 
 * @author yankefei
 *
 */
public class LifeCycleNGTest {

	/**
	 * 每个测试用例都会重新创建当前的Class实例，可以看到Constructor执行了两次。
	 */
	public LifeCycleNGTest() {
		super();
		System.out.println("<<TestNG Constructor Method>>");
	}

	@BeforeSuite
	public static void beforeSuite() {
		System.out.println("<<Before Suite>>");
	}

	@BeforeClass
	public static void beforeClass() {
		System.out.println("<<Before Class>>");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("<<Before Method>>");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("<<After Class>>");
	}

	@AfterMethod
	public void afterMethod() {
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

	@BeforeTest
	public void beforeTest() {
		System.out.println("<<Before Test>>");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("<<After Test>>");
	}
	
	@AfterSuite
	public static void afterSuite() {
		System.out.println("<<After Suite>>");
	}
}
