package com.code.demo.simple;

import java.math.BigInteger;

public class BigIntegerTest {

	public static void main(String[] args) {
		String a = "abc";
		BigInteger aBigInteger2 = new BigInteger(a, 16);
		BigInteger aBigInteger3 = new BigInteger(a, 36);

		System.out.println(Character.digit('0', 36));

		System.out.println(aBigInteger2);
		System.out.println(aBigInteger3);
	}
}
