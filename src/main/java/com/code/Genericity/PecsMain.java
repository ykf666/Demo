package com.code.Genericity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * producer extend consumer super
 * <p>
 * Created by yankefei on 2020/4/24.
 */
public class PecsMain {

    /**
     * Producer extends Consumer super  生产者使用extends，consumer使用super
     * 这里的生产者和消费者是相对容器而言的，如果容器是需要添加数据的，则该容器为消费者；如果是需要从容器获取数据，则容器为生产者
     * <p>
     * 生产者只能对外提供数据，不可以写入数据，数据来源于赋值操作（将参数化类型为子类的容器赋值过来）
     * 消费者表示只能向容器中写入数据，不能读取（只能以Object来接收）
     * 这里的extends和super指的是声明类型和参数化类型的关系,
     * 如下所示：等号左侧类型为声明类型，右侧为参数化类型
     * List<? extends Number> intList = new ArrayList<Integer>();
     * private List<? super Number> intList2 = new ArrayList<Number>();
     *
     * @param args
     */
    public static void main(String[] args) {
        //向上造型一个泛型对象的引用
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple());
        List<? extends Fruit> fruits = apples;
        //事实上，不能往<? extends T>中添加任何元素，因为编译器不知道T的子类型到究竟是什么
        //但另一方面，当我们读取数据的时候，能够确保数据一定是T的子类型
        //compile error
//        fruits.add(new Apple());
//        fruits.add(new Fruit());
        Fruit fruit = fruits.get(0);
        fruit.print();

        //向下造型一个泛型对象的引用
        List<Fruit> fruits2 = new ArrayList<>();
        List<? super Apple> apples2 = fruits2;
        apples2.add(new GreenApple());
        //取出的元素类型，并不确定是那个具体类型，所以无法赋值
        //compile error
        //Fruit fruit2 = apples2.get(0);

        //示例
        Stack<Number> stackNumber = new Stack();
        Iterable<Integer> integers = new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return null;
            }
        };
        //此时对于integers集合容器，是数据的生产者，需要从它读取数据放入stackNumber中，故需要使用<? extend E>
        //Iterable<? extends E>可以容纳任何E的子类
//        stackNumber.pushAll(integers);
        stackNumber.pushAllExtends(integers);
        Collection<Object> collection = new Collection<Object>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Object> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Object o) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        //这里collection就是消费者，需要写入数据，此时需要用<? super E>
        //<? super E>可容纳任何E类型
//        stackNumber.popAll(collection);
        stackNumber.popAllSuper(collection);
    }

    private static class Fruit {

        public void print() {
            System.out.println("Fruit");
        }

    }

    private static class Apple extends Fruit {
        @Override
        public void print() {
            System.out.println("Apple");
        }
    }

    private static class GreenApple extends Apple {
        @Override
        public void print() {
            System.out.println("GreenApple");
        }
    }

    private static class Stack<E> {

        int initialCapacity = 10;
        int itemCount = 0;

        Object[] items = new Object[initialCapacity];

        public void push(E e) {
            items[itemCount] = e;
            itemCount++;
        }

        public E pop() {
            E obj = (E) items[itemCount - 1];
            itemCount--;
            return obj;
        }

        public boolean isEmpty() {
            if (itemCount == 0) {
                return true;
            }
            return false;
        }

        public void pushAll(Iterable<E> src) {
            for (E e : src) {
                push(e);
            }
        }

        public void pushAllExtends(Iterable<? extends E> src) {
            for (E e : src) {
                push(e);
            }
        }

        public void popAll(Collection<E> dst) {
            if (!isEmpty()) {
                for (int i = 0; i < itemCount; i++) {
                    dst.add(pop());
                }
            }
        }

        public void popAllSuper(Collection<? super E> dst) {
            if (!isEmpty()) {
                for (int i = 0; i < itemCount; i++) {
                    dst.add(pop());
                }
            }

        }
    }

}