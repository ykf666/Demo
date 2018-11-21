package com.code.demo.Unit.testng;

import org.testng.annotations.Test;

public class GroupsTest {
	
	@Test(groups = { "functest", "checkintest" })
	public void testMethod1() {
		System.out.println("groups = { functest, checkintest }");
	}

	@Test(groups = { "functest", "checkintest" })
	public void testMethod2() {
		System.out.println("groups = { functest, checkintest }");
	}

	@Test(groups = { "functest" })
	public void testMethod3() {
		System.out.println("groups = { functest }");
	}

	@Test(groups = { "checkintest" })
	public void testMethod4() {
		System.out.println("groups = { checkintest }");
	}

}