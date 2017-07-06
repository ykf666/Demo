package com.code.demo.unit.junit.thread;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class A {
	
	@Test
	public void a() {
		assertThat(3, equalTo(1));
	}

	@Test
	public void b() {
		assertThat(3, not(1));
	}
}
