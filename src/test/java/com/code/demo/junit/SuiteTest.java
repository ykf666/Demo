package com.code.demo.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculatorTest.class, StudentTest.class })
public class SuiteTest {
	//会把上面两个测试类的所有测试方法执行一遍
}
