package com.code.demo.Unit.junit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.code.demo.Unit.Student;

public class ExceptionTest {
	
	@BeforeClass
	public static void beforeTest(){
		/**
		 * 1、在所有方法运行前被执行
		 * 2、static修饰
		 * 3、适合加载配置文件
		 */
	}
	
	@AfterClass
	public static void AfterTest(){
		/**
		 * 1、在所有方法运行结束后被执行
		 * 2、static修饰
		 * 3、用于资源的清理关闭，如数据库
		 */
	}
	
	@Before
	public void beforeMethod(){
		/**
		 * 在每一个测试方法运行前被执行
		 */
	}

	@After
	public void AfterMethod(){
		/**
		 * 在每一个测试方法运行结束后被执行
		 */
	}
	
	@Ignore
	public void testCase0(){
		/**
		 * 此测试方法会被忽略
		 */
		System.out.println("look at me");
	}
	
	/**
	 * JUnit4中包含两个注解@Rule和@ClassRule用于修饰Field或返回Rule的 Method，Rule是一组实现了TestRule接口的共享类，提供了验证、监视TestCase和外部资源管理等能力。
	 * JUnit提供了以下几个Rule实现，必要时也可以自己实现Rule:
	 * 		Verifier: 验证测试执行结果的正确性;
	 * 		ErrorCollector: 收集测试方法中出现的错误信息，测试不会中断，如果有错误发生测试结束后会标记失败;
	 * 		ExpectedException: 提供灵活的异常验证功能;
	 * 		Timeout: 用于测试超时的Rule;
	 * 		ExternalResource: 外部资源管理;
	 * 		TemporaryFolder: 在JUnit的测试执行前后，创建和删除新的临时目录;
	 *		TestWatcher: 监视测试方法生命周期的各个阶段;
	 *		TestName: 在测试方法执行过程中提供获取测试名字的能力;
	 * 简单的说就是提供了测试用例执行过程中一些通用功能的共享的能力，使我们不必重复编写一些功能类似的代码。
	 * JUnit用于标注Rule的注解包括@Rule和@ClassRule，区别在于作用域不同@Rule的作用域是测试方法，@ClassRule则是测试Class。
	 */
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	// 达到2s，终止循环
	@Ignore
	@Test(timeout = 2000)
	public void testLoop() throws Exception {
		int i=0;
		while (true) {
			Thread.sleep(2000);
			System.out.print(i++);
			
		}
	}
	
	/**
	 * 单元测试检查抛出预期异常的方式
	 * 1、@Test(expected = *Exception.class)
	 * 2、如果要使用JUnit框架中的ExpectedException类，需要声明ExpectedException异常
	 * 3、Try/catch with assert/fail，在JUnit4之前的版本中，使用try/catch语句块检查异常
	 * 推荐第二种
	 */
	@Test
	public void testExceptionCase () {
		Student student = new Student("mike", 0);
		student.checkAge();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExceptionCase1 () {
		Student student = new Student("mike", 0);
		student.checkAge();
	}
	
	@Test
	public void testExceptionCase2 () {
		Student student = new Student("mike", 0);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("age should be > 0");
		student.checkAge();
	}
	
	@Test
	public void testExceptionCase3 () {
		Student student = new Student("mike", 0);
		try {
			student.checkAge();
			fail("no excepted IllegalArgumentException for method checkAge");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("age should be > 0"));
		}
	}
	
}
