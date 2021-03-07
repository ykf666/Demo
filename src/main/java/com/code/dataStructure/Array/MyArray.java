package com.code.dataStructure.Array;

import java.util.Arrays;

/**
 * Created by yankefei on 2020/2/1.
 */
public class MyArray<T> {

    private T[] data;
    private int size;

    public MyArray(int capacity) {
        data = (T[]) new Object[capacity];
    }

    //无参构造函数
    public MyArray() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        data[index] = element;
    }

    public boolean contain(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element) {
                return true;
            }
        }
        return false;
    }

    public int find(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is illegal");
        }
        if (size == data.length) {
            this.resize(2 * data.length);
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void addLast(T element) {
        this.add(size, element);
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        T ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length / 4 && data.length / 2 != 0) {
            this.resize(data.length / 2);
        }
        return ret;
    }

    @Override
    public String toString() {
        T[] a = Arrays.copyOf(data, size);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Array: size=%d, capacity=%d, ", size, data.length));
        stringBuilder.append(Arrays.toString(a));
        return stringBuilder.toString();
    }

}
