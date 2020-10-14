package com.zengyu.bingfa.thread;

import java.util.Random;

/**
 * @Classname Match
 * @Description 使用继承thread的方式实现多线程
 * @Date 2020-10-12 15:42
 * @Created by zengyu
 */
public class Match {
    public static void main(String[] args) {
        Runner liu = new Runner(); //创建一个新的线程
        Runner zeng = new Runner(); //创建一个新的线程
        Runner peng = new Runner(); //创建一个新的线程
        liu.setName("刘翔");
        zeng.setName("曾宇");
        peng.setName("彭文森");
        liu.start(); //启动线程
        zeng.start();
        peng.start();
    }
}

class Runner extends Thread {
    @Override
    public void run() {
        Integer speed = new Random().nextInt(20);
        for (int i = 0; i < speed; i++) {
            try {
                Thread.sleep(1000); //当前线程休眠一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //this.getName 当前线程的名字
            System.out.println(this.getName() + "已前进" + (i * speed) + "米(" + speed + "米/秒");
        }
    }
}

