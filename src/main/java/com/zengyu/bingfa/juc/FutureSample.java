package com.zengyu.bingfa.juc;

import java.util.concurrent.*;

/**
 * @Classname Future
 * @Description TODO
 * @Date 2020-10-14 13:49
 * @Created by zengyu
 */
public class FutureSample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 2; i <= 10000; i++) {
            Computer c = new Computer();
            c.setNumber(i);
            //future是对用于计算的线程进行监听，因为计算是在其他线程中进行执行的，所以这个返回结果的过程是异步的
            Future<Boolean> submit = executorService.submit(c);//将c对象提交到线程池，如有空闲线程立即执行里面的call方法
            try {
                Boolean r = submit.get(); //用于获取返回值,如果线程内部的call没有执行完成，则进入等待状态，直到计算完成
                if (r) {
                    System.out.println(i);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}

class Computer implements Callable<Boolean> {
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public Boolean call() throws Exception {
        boolean isPrime = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
