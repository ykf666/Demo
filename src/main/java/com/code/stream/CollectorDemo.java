package com.code.stream;

import org.apache.commons.collections4.MapUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yankefei on 2018/11/29.
 */
public class CollectorDemo {

    public static void main(String[] args) {

        // 测试数据
        List<Student> students = Arrays.asList(
                new Student("小明", 10, Gender.MALE, Grade.ONE),
                new Student("大明", 9, Gender.MALE, Grade.THREE),
                new Student("小白", 8, Gender.FEMALE, Grade.TWO),
                new Student("小黑", 13, Gender.FEMALE, Grade.FOUR),
                new Student("小红", 7, Gender.FEMALE, Grade.THREE),
                new Student("小黄", 13, Gender.MALE, Grade.ONE),
                new Student("小青", 13, Gender.FEMALE, Grade.THREE),
                new Student("小紫", 9, Gender.FEMALE, Grade.TWO),
                new Student("小王", 6, Gender.MALE, Grade.ONE),
                new Student("小李", 6, Gender.MALE, Grade.ONE),
                new Student("小马", 14, Gender.FEMALE, Grade.FOUR),
                new Student("小刘", 13, Gender.MALE, Grade.FOUR));

        //得到所有学生的年龄列表
        //lambda中尽量使用方法引用，这样编译时不会多生成一个lambda$main$0这样的方法
        List<Integer> ages = students.stream().map(Student::getAge).collect(Collectors.toList());
        Set<Integer> ages2 = students.stream().map(o -> o.getAge()).collect(Collectors.toSet());
        Set<Integer> ages3 = students.stream().map(Student::getAge).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("所有学生的年龄：" + ages);
        System.out.println("所有学生的年龄：" + ages2);
        System.out.println("所有学生的年龄：" + ages3);

        //统计汇总信息
        IntSummaryStatistics intSummaryStatistics = students.stream().collect(Collectors.summarizingInt(Student::getAge));
        System.out.println("年龄的汇总信息：" + intSummaryStatistics);

        //分块
        final Map<Boolean, List<Student>> map = students.stream().collect(Collectors.partitioningBy(s -> s.getGender()
                == Gender.FEMALE));
        MapUtils.verbosePrint(System.out, "男女生列表：", map);

        //分组
        Map<Grade, List<Student>> map1 = students.stream().collect(Collectors.groupingBy(Student::getGrade));
        MapUtils.verbosePrint(System.out, "学生班级列表：", map1);

        //得到所有班级学生的个数
        final Map<Grade, Long> map2 = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors
                .counting()));
        MapUtils.verbosePrint(System.out, "各班级学生个数：", map2);
    }

}

/**
 * 学生 对象
 */
class Student {
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 班级
     */
    private Grade grade;

    public Student(String name, int age, Gender gender, Grade grade) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "[name=" + name + ", age=" + age + ", gender=" + gender
                + ", grade=" + grade + "]";
    }
}

/**
 * 性别
 */
enum Gender {
    MALE, FEMALE
}

/**
 * 班级
 */
enum Grade {
    ONE, TWO, THREE, FOUR;
}
