package com.concurrent.thread;

/**
 * @Classname DeadLock
 * @Description TODO
 * @Date 2020-10-13 10:43
 * @Created by zengyu
 */
public class DeadLock {
    private static final String fileA = "A文件";
    private static final String fileB = "B文件";

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (fileA) { //打开文件A，线程独占
                        System.out.println(this.getName() + ":文件A写入");
                        synchronized (fileB) {
                            System.out.println(this.getName() + ":文件B写入");
                        }
                        System.out.println(this.getName() + "：所有文件保存");
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (fileB) { //打开文件A，线程独占
                        System.out.println(this.getName() + ":文件B写入");
                        synchronized (fileA) {
                            System.out.println(this.getName() + ":文件A写入");
                        }
                        System.out.println(this.getName() + "：所有文件保存");
                    }
                }
            }
        }.start();
    }
}
