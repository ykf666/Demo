package com.code.designPatterns.Iterator;

import java.util.Iterator;

/**
 * Created by yankefei on 2020/12/29.
 */
public class Main {

    public static void main(String[] args) {
        BookList books = new BookList();
        books.add(new Book("book1"));
        books.add(new Book("book2"));
        books.add(new Book("book3"));
        books.add(new Book("book4"));
        books.add(new Book("book5"));

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.getName().equals("book2")) {
                iterator.remove();
                continue;
            }
            System.out.println("迭代器循环：" + b.getName());
        }
        for (Book b : books) {
            System.out.println("for循环：" + b.getName());
        }
    }

}
