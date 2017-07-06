package com.code.demo.unit.testng;

import org.testng.annotations.DataProvider;

public class UserDataProvider {
	
	//注意static
	@DataProvider(name="users2")
	public static Object[][] users2() {
		return new Object[][] { { "jack","13" }, { "mike","12" }, { "lucy","16" } };
	}
	
}
