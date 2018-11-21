package com.code.demo.Unit.testng;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.code.demo.Unit.App;

public class AppNGTest {

	@Test
	public void testCase1() {
		Assert.assertEquals(new App().getStr(), "Hello World");
	}

	@Test
	public void testCase2() {
		Assert.assertNotEquals(new App().add(2, 3), 6);
	}

	@Test
	public void testCase3() {
		Assert.assertTrue(StringUtils.isNotBlank("123"));
	}
		
	@Test
	public void testCase4() {
		Assert.assertTrue(StringUtils.isNotBlank(""), "hello false");
	}

	@Test
	public void testCase5() {
		Assert.assertFalse(new App().add(2, 3) == 6);
	}
	
	@Test
	public void testCase6() {
		Assert.assertFalse(StringUtils.isBlank(""), "hello false");
	}

	@Test
	public void testCase7() {
		Assert.assertSame(new App().getStr(), "Hello World");
	}

	@Test
	public void testCase8() {
		Assert.assertSame(new App().getStr(), new String("Hello World"));
	}
	
	@Test
	public void testCase9() {
		Assert.assertNotSame(new App().getStr(), new String("Hello World"));
	}
	
	//hamcrest断言
	@Test
	public void testCase10() {
		assertThat(new App().getStr(), equalTo("Hello World"));
	}
	
}
