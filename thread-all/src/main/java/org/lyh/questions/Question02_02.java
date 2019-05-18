package org.lyh.questions;

import java.util.concurrent.CountDownLatch;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2018/6/23 22:34
 */
public class Question02_02 {

    static  class PrintCharTask implements Runnable{

        private char ch;
        private CountDownLatch latch;
        private PrintCharTask nextTask;

        public PrintCharTask(char ch) {
            this.ch = ch;
            this.latch = new CountDownLatch(1);
        }

        public void run() {
            for (int i = 0; i < 50; i ++){
                synchronized (PrintCharTask.class){
                    waitRun();
                    System.out.println(ch);
                    latch = new CountDownLatch(1);
                    nextTask.beginRun();
                }
            }
        }

        public void waitRun(){
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void beginRun(){
            latch.countDown();
        }

        public void setNextTask(PrintCharTask nextTask) {
            this.nextTask = nextTask;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        PrintCharTask charTaskA = new PrintCharTask('A');
        PrintCharTask charTaskB = new PrintCharTask('B');
        PrintCharTask charTaskC = new PrintCharTask('C');

        charTaskA.setNextTask(charTaskB);
        charTaskB.setNextTask(charTaskC);
        charTaskC.setNextTask(charTaskA);

        Thread a = new Thread(charTaskA,"thread-a");
        Thread b = new Thread(charTaskA,"thread-b");
        Thread c = new Thread(charTaskA,"thread-c");

        a.start();
        b.start();
        c.start();

        charTaskA.beginRun();
    }
}
