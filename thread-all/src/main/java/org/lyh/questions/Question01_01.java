package org.lyh.questions;

// wait 释放掉锁，并等待被环境。所以wait必须在synchronized中，因为肯定要先有锁才能释放锁。wait方法结束后，此线程将重新获得锁
// notify 唤醒监视在此锁上的一个线程，
// notify 后，当前线程不会马上释放该对象锁，wait 所在的线程并不能马上获取该对象锁，要等到程序退出 synchronized 代码块后，当前线程才会释放锁，wait所在的线程也才可以获取该对象锁

public class Question01_01 {
    public static boolean runMainThread = false;

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for(int i = 0 ; i < 50; i ++){
                    synchronized (lock){
                        if(runMainThread){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int j = 0; j < 2; j ++){
                            System.out.println("sub thread: " + j);
                        }
                        runMainThread = true;
                        lock.notify();
                    }
                }
            }
        });
        thread.start();
        for (int i = 0 ; i < 50;i ++){
            synchronized (lock){
                if(! runMainThread){
                    lock.wait();
                }
                for (int j = 0 ; j < 10; j++){
                    System.out.println("main thread: " + j);
                }
                runMainThread = false;
                lock.notify();
            }
        }
    }
}
