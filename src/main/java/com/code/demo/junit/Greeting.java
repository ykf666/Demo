package com.code.demo.junit;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * 
 * Runner是一个抽象类，是JUnit的核心组成部分。用于运行测试和通知Notifier运行的结果。
 * JUnit使用@RunWith注解标注选用的Runner，由此实现不同测试行为。 
 * 
 * BlockJUnit4ClassRunner：这个是JUnit的默认Runner，平时我们编写的JUnit不添加@RunWith注解时使用的都是这个Runner。 
 * Suite：用来执行分布在多个类中的测试用例
 * Parameterized：Parameterized继承自Suit，从这个身世和名字应该可以猜到一些因果了。
 * 		Parameterized是在参数上实现了Suit——修饰一个测试类，但是可以提供多组构造函数的参数用于测试不同场景。
 *
 */
@RunWith(Parameterized.class)
public class Greeting {
	
	private String message;
	
	public Greeting(String message) {
		this.message = message;
		System.out.println("....");
	}
	
	@Parameters    
    public static List<String[]> getParams() {
        return Arrays.asList(new String[][]{{"hello"},{"hi"},{"你好"},});
    }
	
    @Test
	public void greet(){
		System.out.println(message);
	}
}
