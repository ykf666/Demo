package com.code.demo.unit.junit;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

import com.code.demo.unit.App;

public class AppTest {

	// @Test
	public void testMethod1() {
		Assert.assertEquals(new App().getStr(), equalTo("Hello World"));
	}

	/**
	 * hamcrest断言风格
	 */
	// 核心匹配
	@Test
	public void testCase1() {
		assertThat(new App().getStr(), equalTo("Hello World"));
	}

	@Test
	public void testCase2() {
		assertThat(new App().getStr(), not(equalTo("haha")));
	}

	// 以Hello开始，包含World的字符串
	@Test
	public void testCase3() {
		assertThat(new App().getStr(),
				allOf(startsWith("Hello"), containsString("World")));
	}

	// 部分满足条件，或关系
	@Test
	public void testCase4() {
		assertThat(
				new App().getStr(),
				anyOf(startsWith("Hello"), containsString("orl"),
						endsWith("ld")));
	}

	// 同时包含，与关系
	@Test
	public void testCase5() {
		assertThat(new App().getStr(),
				both(containsString("Hello")).and(containsString("World")));
	}

	@Test
	public void testCase6() {
		assertThat(new App().getStr(),
				either(containsString("Hello")).or(containsString("moto")));
	}

	// 集合中的每一个元素都是String类型
	@Test
	public void testCase7() {
		assertThat(new App().getList(), everyItem(isA(String.class)));
	}

}
