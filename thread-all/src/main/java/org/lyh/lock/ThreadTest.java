package org.lyh.lock;

public class ThreadTest implements Runnable {

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    boolean flag = false;

    public void run() {
        while (true) {
            if (flag) {
                dieLock(lock1, lock2);
            } else {
                dieLock(lock2, lock1);
            }
        }
    }

    private void dieLock(Object lock1, Object lock2) {
        synchronized (lock1) {
            synchronized (lock2) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    //
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] arr) {
        ThreadTest r1 = new ThreadTest();
        ThreadTest r2 = new ThreadTest();
        r2.flag = true;

        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);

        thread1.start();
        thread2.start();
    }
}