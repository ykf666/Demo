package com.code.demo.Unit.junit;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TheoryTest {
	
	@DataPoint
	public static String nameValue1 = "Tony";

//	@DataPoint
//	public static String nameValue2 = "Jim";

	@DataPoint
	public static int ageValue1 = 10;

//	@DataPoint
//	public static int ageValue2 = 20;
	
	@Theory
	public void testMethod(String name, int age) {
		System.out.println(String.format("%s's age is %s", name, age));
	}
	
	@DataPoints
	public static String[] names = {"Jack", "Lucy"};
	
	@DataPoints
	public static int[] ages = {40, 50};
	
	@Theory
	public void testMethod2(String name, int age)
	{
		System.out.println(String.format("%s's age is %s", name, age));
	}
	
}
