package com.zengyu.bingfa.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname CountDownSample
 * @Description TODO
 * @Date 2020-10-13 15:05
 * @Created by zengyu
 */
public class CountDownSample {
    private static int count = 0;

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        //倒计时锁
        CountDownLatch countDownLatch = new CountDownLatch(10000); //countDownLatch的总数和for循环次数相同
        for (int i = 1; i <= 10000; i++) {
            final int index = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (CountDownSample.class) {
                        try {
                            count = count + index;
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            countDownLatch.countDown(); //计数器减一
                        }
                    }
                }
            });
        }
        try {
            countDownLatch.await(); //堵塞当前线程,直到countDownLatch为0才会继续进行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        threadPool.shutdown();
    }
}
