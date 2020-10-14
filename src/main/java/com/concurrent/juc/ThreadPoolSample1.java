package com.concurrent.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname ThreadPoolSample
 * @Description TODO
 * @Date 2020-10-13 13:40
 * @Created by zengyu
 */
public class ThreadPoolSample1 {
    public static void main(String[] args) {
        //ExecutorService 用于管理线程池
        //创建一个可缓存线程池
        //特点：无限大,如果线程池中没有可用线程则创建线程,有空闲线程则利用
        ExecutorService threadPool = Executors.newCachedThreadPool();
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
