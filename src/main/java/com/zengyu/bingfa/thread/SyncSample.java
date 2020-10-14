package com.zengyu.bingfa.thread;

import java.util.Random;

import static sun.misc.PostVMInitHook.run;

/**
 * @Classname SyncSample
 * @Description TODO
 * @Date 2020-10-12 16:39
 * @Created by zengyu
 */
public class SyncSample {
    public static void main(String[] args) {
        Couplet couplet = new Couplet();
        for (int i = 0; i < 10000; i++) {
            new Thread() {
                @Override
                public void run() {
                    int r = new Random().nextInt(2);
                    if (r % 2 == 0)
                        couplet.first();
                    else
                        couplet.second();
                }
            }.start();
        }
    }
}

class Couplet {
    final Object lock = new Object();

    public void first() {
        synchronized (lock) {  //同步代码块锁 同一时间只允许一个线程执行访问这个方法  其他等待
            System.out.print("琴");
            System.out.print("瑟");
            System.out.print("琵");
            System.out.print("琶");
            System.out.println();
        }
    }

    public void second() {
        synchronized (lock) {
            System.out.print("魑");
            System.out.print("魅");
            System.out.print("魍");
            System.out.print("魉");
            System.out.println();
        }
    }
}
