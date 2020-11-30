package com.code.designPatterns.Iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * Created by yankefei on 2020/11/30.
 */
public class BookList implements Iterable<Book> {

    private LinkedList<Book> books = new LinkedList<>();

    public void add(Book book) {
        books.add(book);
    }

    public void delete(Book book) {
        books.remove(book);
    }

    public int size() {
        return books.size();
    }

    @Override
    public Iterator<Book> iterator() {
        return books.iterator();
    }

    @Override
    public void forEach(Consumer<? super Book> action) {
        for (Book book : this) {
            action.accept(book);
        }
    }


    @Override
    public Spliterator<Book> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
