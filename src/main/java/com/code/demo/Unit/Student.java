package com.code.demo.Unit;

public class Student {

	private String name;
	private int age;
	
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public void checkAge() {
		if (age <= 0)
			throw new IllegalArgumentException("age should be > 0");
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return this.getName() + ":" + this.getAge();
	}

}
