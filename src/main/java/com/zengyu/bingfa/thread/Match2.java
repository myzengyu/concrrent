package com.zengyu.bingfa.thread;

import java.util.Random;

/**
 * @Classname Match2
 * @Description 实现runnable接口
 * @Date 2020-10-12 15:56
 * @Created by zengyu
 */
public class Match2 {
    public static void main(String[] args) {
        Runner2 liu = new Runner2(); //创建一个新的线程
        Runner2 zeng = new Runner2(); //创建一个新的线程
        Runner2 peng = new Runner2(); //创建一个新的线程
        Thread thread1 = new Thread(liu);
        Thread thread2 = new Thread(zeng);
        Thread thread3 = new Thread(peng);
        thread1.setName("刘翔");
        thread2.setName("曾宇");
        thread3.setName("彭文森");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Runner2 implements Runnable {
    @Override
    public void run() {
        Integer speed = new Random().nextInt(100);
        for (int i = 0; i < speed; i++) {
            try {
                Thread.sleep(1000); //当前线程休眠一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Thread.currentThread().getName() 当前线程的名字
            System.out.println(Thread.currentThread().getName() + "已前进" + (i * speed) + "米(" + speed + "米/秒");
        }
    }
}
