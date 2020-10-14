package com.zengyu.bingfa.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname ThreadPoolSample
 * @Description TODO
 * @Date 2020-10-13 13:40
 * @Created by zengyu
 */
public class ThreadPoolSample2 {
    public static void main(String[] args) {
        //ExecutorService 用于管理线程池
        //创建一个定长线程池
        //特点：固定线程总数 空闲线程用于执行任务 如果线程都在使用 后续线程任务则处于等待 在线程池中的线程
        //如果任务处于等待的状态 备选的等待算法为（默认）FIFO（先进先出） LIFO（先进后出）
        //执行任务后再执行后续的任务
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            final int index = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + index);
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //shutdown() 代表关闭线程池 等待所有线程完成
        //shutdownNow(); 代表立即终止线程池 不管线程是否完成
        threadPool.shutdown();
    }
}
