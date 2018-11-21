package com.code.demo.Simple;

public class IntegerDemo {

	public static void main(String[] args) {
		int a = 1;
		int b = 1;
		System.out.println(a == b);

		int c = 128;
		int d = 128;
		System.out.println(c == d);

		Integer a1 = 1;
		Integer b1 = 1;
		System.out.println(a1 == b1);
		System.out.println(a1.equals(b1));

		Integer c1 = 128;
		Integer d1 = 128;
		System.out.println(a1 == b1);
		System.out.println(c1.equals(d1));

	}

}
