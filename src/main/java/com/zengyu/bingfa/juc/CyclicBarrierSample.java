package com.zengyu.bingfa.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname CyclicBarrier
 * @Description TODO
 * @Date 2020-10-13 17:34
 * @Created by zengyu
 */
public class CyclicBarrierSample {
    //循环屏障
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 20; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    go();
                }
            });
        }
        threadPool.shutdown();
    }

    private static void go() {
        System.out.println(Thread.currentThread().getName() + "线程已经准备好了");
        try {
            cyclicBarrier.await(); //设置屏障点 当累计5个线程都准备好 才运行后面的代码
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "开始运行");
    }
}
