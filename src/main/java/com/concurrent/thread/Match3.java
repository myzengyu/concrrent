package com.concurrent.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Classname Match3
 * @Description
 * @Date 2020-10-12 16:14
 * @Created by zengyu
 */
public class Match3 {
    public static void main(String[] args) {
        //创建一个线程池，里面有3个空线程 Executors是调度器对线程池进行管理
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Runner3 liu = new Runner3();
        Runner3 zeng = new Runner3();
        Runner3 peng = new Runner3();
        liu.setName("刘翔");
        zeng.setName("曾宇");
        peng.setName("彭文森");
        //将这个对象扔到线程池中，线程池自动分配一个线程来运行刘翔这个对象的call方法
        //Future用于接收线程内部call方法的返回值
        Future<Integer> submit = executorService.submit(liu);
        Future<Integer> submit1 = executorService.submit(zeng);
        Future<Integer> submit2 = executorService.submit(peng);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭线程池，施放所有线程
        executorService.shutdown();
        try {
            System.out.println("刘翔累计跑了" + submit.get() + "米");
            System.out.println("曾宇累计跑了" + submit1.get() + "米");
            System.out.println("彭文森累计跑了" + submit2.get() + "米");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class Runner3 implements Callable<Integer> {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    //实现Callable接口，可以允许我们线程返回值 或抛出异常
    @Override
    public Integer call() throws Exception {
        Integer speed = new Random().nextInt(100);
        Integer distance = 0;//总共跑得距离
        for (int i = 0; i < 100; i++) {
            Thread.sleep(10);
            distance = i * speed;
            System.out.println(this.name + "已前进" + distance + "米(" + speed + "米/秒");
        }
        return distance;
    }
}
