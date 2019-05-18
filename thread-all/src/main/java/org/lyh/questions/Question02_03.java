package org.lyh.questions;

import java.util.concurrent.Semaphore;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2018/6/24 23:38
 */
public class Question02_03 {
    private static class Outer extends Thread{
        private char ch;
        private Semaphore semaphore;
        private Semaphore nextSemaphore;

        public Outer(String name, char ch) {
            super(name);
            this.ch = ch;
            this.semaphore = new Semaphore(1);
        }

        @Override
        public void run() {
            for (int i = 0; i < 50 ; i++){
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(ch);
                nextSemaphore.release();
            }
        }
    }
    public static void main(String[] args) {
        Outer a = new Outer("thread-a",'A');
        Outer b = new Outer("thread-b",'B');
        Outer c = new Outer("thread-c",'B');

        a.nextSemaphore = b.semaphore;
        b.nextSemaphore = c.semaphore;
        c.nextSemaphore = a.semaphore;

        a.start();
        b.start();
        c.start();

        a.semaphore.release();
    }
}
