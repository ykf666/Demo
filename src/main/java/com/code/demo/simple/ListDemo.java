package com.code.demo.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yan.kefei
 * @date 2018/6/4 16:55
 */
public class ListDemo {

    public static void main(String[] args) {
        List<String> alist = new ArrayList<>();
        alist.add("A");
        alist.add("B");
        alist.add("C");
        List<String> blist = new ArrayList<>();
        blist.add("B");
        blist.add("C");
        blist.add("D");

        // 交集
//        alist.retainAll(blist);
//        printList(alist);

        // 并集
//        alist.addAll(blist);
//        printList(alist);

        // 无重复并集

        blist.removeAll(alist);
        alist.addAll(blist);
        System.out.println(alist);

        // 差集
//        alist.removeAll(blist);
//        printList(alist);
    }

}
