package com.zengyu.bingfa.juc;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname ConcurrentHashMapSample
 * @Description TODO
 * @Date 2020-10-14 14:40
 * @Created by zengyu
 */
public class ConcurrentHashMapSample {

    public static int users = 100; //同时模拟并发下载的用户数
    public static int downTotal = 50000; //用户下载的真是总数
    public static ConcurrentHashMap<Integer, Integer> count = new ConcurrentHashMap<>(); //计数器

    public static void main(String[] args) {
        //调度器 jdk1.5后提供的concurrent包对于并发的支持
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量，用于模拟并发的人数
        final Semaphore semaphore = new Semaphore(users);
        System.out.println("开始下载-----------------------------");
        for (int i = 0; i < downTotal; i++) {
            final Integer index = i;
            executorService.execute(() -> {
                //模拟n个用户并发访问并下载
                try {
                    semaphore.acquire();
                    count.put(index, index);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown(); //关闭调度服务
        System.out.println("下载总数:" + count.size());
    }


}
