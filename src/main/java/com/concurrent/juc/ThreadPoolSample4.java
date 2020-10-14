package com.concurrent.juc;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Classname ThreadPoolSample
 * @Description TODO
 * @Date 2020-10-13 13:40
 * @Created by zengyu
 */
public class ThreadPoolSample4 {
    public static void main(String[] args) {
        //ExecutorService 用于管理线程池
        //创建一个可调度线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//        scheduledThreadPool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("延迟三秒执行");
//            }
//        },3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date() + "延迟一秒执行,每三秒执行一次");
            }
        }, 1, 3, TimeUnit.SECONDS);
        scheduledThreadPool.isTerminated();
    }
}
