package com.code.basic.Unit.testng;

import org.testng.annotations.Test;

import com.code.basic.Unit.Student;

public class ExceptionNGTest {

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testExceptionCase1() {
		Student student = new Student("mike", 0);
		student.checkAge();
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "age should be > 0")
	public void testExceptionCase2() {
		Student student = new Student("mike", 0);
		student.checkAge();
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "age should be > 0", enabled = true)
	public void testExceptionCase3() {
		Student student = new Student("mike", 20);
		student.checkAge();
	}
}
