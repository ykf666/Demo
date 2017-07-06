package com.code.demo.unit.junit;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Parameterized2Test {

	private String name;
	private String age;

	public Parameterized2Test(String name, String age) {
		this.name = name;
		this.age = age;
		System.out.println("....");
	}

	@Parameters
	public static Iterable<Object[]> getParams() {
		return Arrays.asList(new Object[][] { { "mike", "20" },
				{ "jack", "15" }, { "lucy", "18" } });
	}

	@Test
	public void printTest() {
		System.out.println(name + ":" + age);
	}

}
