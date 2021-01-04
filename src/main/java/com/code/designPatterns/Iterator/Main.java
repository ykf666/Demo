package com.code.designPatterns.Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yankefei on 2020/12/29.
 */
public class Main {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("book1"));
        books.add(new Book("book2"));
        books.add(new Book("book3"));
        books.add(new Book("book4"));
        books.add(new Book("book5"));
        books.forEach(item -> System.out.println(item.getName()));
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.getName().equals("book2")) {
                iterator.remove();
                continue;
            }
            System.out.println("迭代器循环：" + b.getName());
        }
        books.forEach(item -> System.out.println(item.getName()));
    }

}
