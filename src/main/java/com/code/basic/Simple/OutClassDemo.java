package com.code.basic.Simple;

/**
 * 内部类测试
 * 内部类其实持有外部类的this引用
 * @author yankefei
 *
 */
public class OutClassDemo {

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
			System.out.println("demo2--外部类变量：" + OutClassDemo.this.age);
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
	// 这种内部类，如果需要使用方法内的局部变量，这个变量必须是final的，jdk8省去了final的显示，但默认还是final的
	// 这个内部类的默认构造方法，实际是传入两个this引用，一个是OutClassDemo的，一个是In的
	public void out_print2(final int x) {
		int y = 10;
		class In {
			public void inPrint() {
				System.out.println("y--" + y);
				System.out.println("demo5--" + x);
				System.out.println("demo5--" + age);
			}
		}

		class In5 extends In {
			@Override
			public void inPrint() {
				System.out.println("demo6--" + y);
			}
		}
		new In().inPrint();
		new In5().inPrint();
	}

	public static void main(String[] args) {
		OutClassDemo.In1 demo1 = new OutClassDemo().new In1();
		demo1.print();

		OutClassDemo.In2 demo2 = new OutClassDemo().new In2();
		demo2.print();

		OutClassDemo.In3 demo3 = new OutClassDemo.In3();
		demo3.print();

		OutClassDemo demo4 = new OutClassDemo();
		demo4.out_print();

		OutClassDemo demo5 = new OutClassDemo();
		demo5.out_print2(17);

	}

	public void out_print() {
		new In4().print();
	}
}
