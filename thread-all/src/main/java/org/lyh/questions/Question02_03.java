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

        Outer(String name, char ch) {
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

        Outer headOuter = null,prevOuter = null;
        int n = 9;
        for (int i = 0 ; i < n; i ++) {
            char ch = (char) ('A' + i);
            Outer outer = new Outer("thread" + ch, ch);
            if (headOuter == null) {
                headOuter = outer;
            }
            if (prevOuter != null) {
                prevOuter.nextSemaphore =outer.semaphore;
            }
            if (i == n -1) {
                outer.nextSemaphore = headOuter.semaphore;
            }
            try {
                outer.semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outer.start();
            prevOuter = outer;
        }

        headOuter.semaphore.release();
    }
}
