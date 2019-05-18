package org.lyh.questions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Question01_02 {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Condition subThread = lock.newCondition();
        final Condition mainThread = lock.newCondition();

        Thread t = new Thread() {
            public void run() {
                int i = 0;
                while (true) {
                    i++;
                    lock.lock();
                    if (i % 11 == 0) {
                        i = 1;
                        waitAndNotify(lock, mainThread, subThread);
                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("次线程执行了" + i + "次");
                }
            }
        };
        t.start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        int i = 0;
        while (true) {
            lock.lock();
            i++;
            if (i % 101 == 0) {
                i = 1;
                waitAndNotify(lock, subThread, mainThread);
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程执行了" + i + "次");
        }
    }

    private static void waitAndNotify(Lock lock, Condition singalThread, Condition waitThread) {
        try {
            singalThread.signal();
            waitThread.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
