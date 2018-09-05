package com.code.demo.CompareObj;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yankefei on 2018/9/3.
 */
public class CompareMain {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        List<Person2> list2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int age = RandomUtils.nextInt(1, 100);

            Person person = new Person();
            person.setName("p" + i);
            person.setAge(age);
            list.add(person);

            Person2 person2 = new Person2();
            person2.setName("p" + i);
            person2.setAge(age);
            list2.add(person2);
        }
        System.out.println("内比较器排序前：" + StringUtils.join(list, " ,"));
        //升序，内部比较器
        Collections.sort(list);
        System.out.println("内比较器排序后：" + StringUtils.join(list, " ,"));

        System.out.println();
        System.out.println("外比较器排序前：" + StringUtils.join(list2, " ,"));
        //外比较器，lambda写法
        list2.sort((final Person2 o1, final Person2 o2) -> o1.getAge() - o2.getAge());
        System.out.println("外比较器（正）排序后：" + StringUtils.join(list2, " ,"));

        //外比较器，lambda逆序排写法
        Comparator<Person2> comparator = (final Person2 o1, final Person2 o2) -> o1.getAge() - o2.getAge();
        list2.sort(comparator.reversed());
        System.out.println("外比较器（反）排序后：" + StringUtils.join(list2, " ,"));
    }

}
