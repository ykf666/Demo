package com.code.demo.Simple;

import java.util.*;

/**
 * @author yan.kefei
 * @date 2018/6/4 16:55
 */
public class ListDemo {

    public static void main(String[] args) {
        new LinkedList<>();
        List<String> alist = new ArrayList<>();
        alist.add("A");
        alist.add("B");
        alist.add("C");
        List<String> blist = new ArrayList<>();
        blist.add("B");
        blist.add("C");
        blist.add("D");

        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(3);
        set2.add(4);
//        for (Iterator<Integer> i = set2.iterator();i.hasNext();){
//            set1.remove(i.next());
//        }
        set1.removeAll(set2);
        System.out.println(set1);

        // 交集
//        alist.retainAll(blist);
//        printList(alist);

        // 并集
//        alist.addAll(blist);
//        printList(alist);

        // 无重复并集

/*        blist.removeAll(alist);
        alist.addAll(blist);
        System.out.println(alist);*/

        // 差集
        alist.removeAll(blist);
        System.out.println(alist);
    }

}
