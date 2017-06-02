package com.code.demo.junit;

public class Student {

	private String name;
	private int age;
	private int math;
	private int english;
	private int chinese;
	
	public Student(String name, int age, int math, int english, int chinese) {
		super();
		this.name = name;
		this.age = age;
		this.math = math;
		this.english = english;
		this.chinese = chinese;
	}

	public int getMath() {
		return math;
	}

	public int getEnglish() {
		return english;
	}

	public int getChinese() {
		return chinese;
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
