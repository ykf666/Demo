package com.code.demo.unit.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ExceptionTest.class, CalculatorTest.class})
public class JunitSuiteTest {
	//会把上面两个测试类的所有测试方法执行一遍
}
