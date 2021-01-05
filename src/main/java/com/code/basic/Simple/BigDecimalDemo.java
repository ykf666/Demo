package com.code.basic.Simple;

import java.math.BigDecimal;

public class BigDecimalDemo {
	
	public static void main(String[] args) {
		
		BigDecimal bigDecimal = new BigDecimal("499");
	    BigDecimal bigDecimal2 = new BigDecimal(100);
	    Float price = bigDecimal.divide(bigDecimal2, 2, BigDecimal.ROUND_HALF_UP).floatValue();
		System.out.println(price);
	}
	
}
