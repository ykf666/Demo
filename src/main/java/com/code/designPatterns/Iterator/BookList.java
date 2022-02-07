package com.code.designPatterns.Iterator;

import java.util.*;

/**
 * 不使用迭代模式时，实现book容器
 * Created by yankefei on 2022/2/1.
 */
public class BookList {

    private List<Book> books;

    private int index;

    public BookList() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
        this.index++;
    }

/*    public void removeBook(Book book) {
        int index = this.books.indexOf(book);
        this.books.remove(index);
        this.index--;
    }


    public boolean hasNext() {
        if (this.index > this.books.size()) {
            return false;
        }
        return true;
    }

    public Book next() {
        return this.books.get(this.index);
    }*/

    public Book get(int index) {
        return this.books.get(index);
    }

    public int size() {
        return this.index;
    }

    public Iterator<Book> iterator() {
        return new BookList().Itr();
    }

    private class Itr implements Iterator<Book> {

        @Override
        public void remove() {
        }

        @Override
        public boolean hasNext() {
            if (this.index > this.books.size()) {
                return false;
            }
            return false;
        }

        @Override
        public Book next() {
            return null;
        }
    }
