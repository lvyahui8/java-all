package org.lyh.lock;

import java.util.concurrent.locks.*;

public class CheckTest implements Runnable {

    static Lock lock = new ReentrantLock();
    static Condition mainThread = lock.newCondition();
    static Condition childThread = lock.newCondition();

    private int cnt = 0;

    public void run() {
        try {
            while (true) {
                lock.lock();
                for (int i = 0; i < 50; i++) {
                    System.out.println("subthread :" + i);
                }

                if (cnt++ > 50) {
                    break;
                }
                mainThread.signal();
                childThread.await();
                lock.unlock();
            }
        } catch (Exception e) {

        }

    }

    public static void main(String[] arr) throws Exception {
        CheckTest checkTest = new CheckTest();
        int mainCnt = 0;
        new Thread(checkTest).start();
        Thread.sleep(100);
        while (true) {
            lock.lock();
            for (int i = 0; i < 100; i++) {
                System.out.println("manthread :" + i);
            }
            if (mainCnt++ >= 50) {
                break;
            }
            childThread.signal();
            mainThread.await();
            lock.unlock();
        }
    }
}