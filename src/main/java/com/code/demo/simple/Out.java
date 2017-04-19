package com.code.demo.simple;

/**
 * 内部类测试
 * 
 * @author yankefei
 *
 */
public class Out {

	private int age = 12;
	private static int age2 = 16;

	// 内部类基本机构
	class In1 {
		public void print() {
			System.out.println("demo1--" + age);
		}
	}

	// 内部类中的变量访问形式
	class In2 {
		private int age = 13;

		public void print() {
			int age = 14;
			System.out.println("demo2--局部变量：" + age);
			System.out.println("demo2--内部类变量：" + this.age);
			System.out.println("demo2--外部类变量：" + Out.this.age);
		}
	}

	// 静态内部类
	static class In3 {
		public void print() {
			System.out.println("demo3--" + age2);
		}
	}

	// 私有内部类
	private class In4 {
		public void print() {
			System.out.println("demo4--" + age);
		}
	}

	// 方法内部类
	public void out_print2(final int x) {
		class In {
			public void inPrint() {
				System.out.println("demo5--" + x);
				System.out.println("demo5--" + age);
			}
		}
		new In().inPrint();
	}

	public static void main(String[] args) {
		Out.In1 demo1 = new Out().new In1();
		demo1.print();

		Out.In2 demo2 = new Out().new In2();
		demo2.print();

		Out.In3 demo3 = new Out.In3();
		demo3.print();

		Out demo4 = new Out();
		demo4.out_print();

		Out demo5 = new Out();
		demo5.out_print2(17);

	}

	public void out_print() {
		new In4().print();
	}
}
