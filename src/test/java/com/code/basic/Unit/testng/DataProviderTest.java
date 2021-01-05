package com.code.basic.Unit.testng;

import java.util.Arrays;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

	@DataProvider(name = "messages")
	public Object[][] messages() {
		return new Object[][] { { "hello" }, { "my" }, { "name" }, { "is" },
				{ "yankefei" } };
	}
	
	@Test(dataProvider = "messages", enabled = true)
	public void sayTest(String message) {
		System.out.println(message + "...");
	}
	
	@DataProvider(name="messages2")
	public Iterator<Object[]> message2(){
		return Arrays.asList(new Object[][]{{"hello"},{"world"}}).iterator();
	}
	
	@Test(dataProvider = "messages2", enabled = true)
	public void sayTest2(String message) {
		System.out.println(message + "...");
	}
	
	@DataProvider(name="users")
	public Object[][] users() {
		return new Object[][] { { "mike","12" }, { "lucy","16" } };
	}
	
	@Test(dataProvider = "users", enabled = true)
	public void userInfoTest(String name, String age) {
		System.out.println(name + ":" + age);
	}
	
	@Test(dataProvider = "users2", dataProviderClass=UserDataProvider.class, enabled = true)
	public void userInfoTest2(String name, String age) {
		System.out.println(name + ":" + age);
	}
	
}