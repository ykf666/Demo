package com.code.demo.unit.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamTest {

	/**
	 * [Parameters] 数组中的参数名必须和testng.xml中的参数名一致，最好的是顺序都一致，以免有影响
	 * 
	 * @param param0
	 *            此处的参数值与配置文件的参数位置第一个等值
	 * @param param1
	 *            此处的参数值与配置文件的参数位置第二个等值
	 */
	@Test
	@Parameters({ "param0", "param1" })
	public void testNGTestParam(String param0, String param1) {
		System.out.println("param0:" + param0 + ", param1:" + param1);
	}
}
