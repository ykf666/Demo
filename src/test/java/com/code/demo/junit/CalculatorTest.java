package com.code.demo.junit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	private static Calculator cal = new Calculator();
	
	@Before
	public void resetResult(){
		cal.clear();
	}
	
	@Test
	public void testAdd() {
		cal.add(8);
		assertThat(cal.getResult(), is(8));
	}

	@Test
	public void testSubstract() {
		Calculator.result = 10;
		cal.substract(2);
		assertThat(cal.getResult(), is(8));
	}

	@Test
	public void testMultiply() {
		Calculator.result = 10;
		cal.multiply(3);
		assertThat(cal.getResult(), is(30));
	}

	@Test
	public void testDivide() {
		Calculator.result = 10;
		cal.divide(2);
		assertThat(cal.getResult(), is(5));
	}

}
