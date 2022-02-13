package com.code.designPatterns.Iterator;

import java.util.Iterator;

/**
 * 迭代模式
 * Created by yankefei on 2020/12/29.
 */
public class Main {

    public static void main(String[] args) {
        BookList bookList = new BookList();
        bookList.addBook(new Book("book1"));
        bookList.addBook(new Book("book2"));
        bookList.addBook(new Book("book3"));
        bookList.addBook(new Book("book4"));
        bookList.addBook(new Book("book5"));
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            System.out.println(book.toString());
        }

        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.getName().equals("book2")) {
                iterator.remove();
                continue;
            }
            System.out.println("迭代器循环：" + b.getName());
        }

/*        List<Book> books = new ArrayList<>();
        books.add(new Book("book1"));
        books.add(new Book("book2"));
        books.add(new Book("book3"));
        books.add(new Book("book4"));
        books.add(new Book("book5"));
        books.forEach(item -> System.out.println(item.getName()));
        Iterator<Book> iterator2 = books.iterator();
        while (iterator2.hasNext()) {
            Book b = iterator2.next();
            if (b.getName().equals("book2")) {
                iterator2.remove();
                continue;
            }
            System.out.println("迭代器循环：" + b.getName());
        }
        books.forEach(item -> System.out.println(item.getName()));*/
    }

}
