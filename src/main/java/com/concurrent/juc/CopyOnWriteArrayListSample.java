package com.concurrent.juc;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Classname CopyOnWriteArratList
 * @Description TODO
 * @Date 2020-10-14 14:30
 * @Created by zengyu
 */
public class CopyOnWriteArrayListSample {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        for (Integer next : list) {
            list.remove(next);
        }
        System.out.println(list);
    }
}
