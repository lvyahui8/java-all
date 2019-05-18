package org.lyh.questions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2018/6/23 21:35
 */
public class Question02_01 {

    public static class Outputer implements Runnable{
        private char ch;
        private Condition thisCondition;
        private Condition nextCondition;
        private ReentrantLock lock;
        private boolean isFirst =  false;

        public void setFirst(boolean first) {
            isFirst = first;
        }

        public Outputer(char ch, ReentrantLock lock, Condition thisCondition, Condition nextCondition) {
            this.ch = ch;
            this.thisCondition = thisCondition;
            this.nextCondition = nextCondition;
            this.lock = lock;
        }

        public void run() {
            for (int i = 0 ; i < 50; i++){
                lock.lock();
                try{

                    System.out.print(ch);
                    Thread.sleep(1);
                    nextCondition.signal();
                    if( i < 49)
                        thisCondition.await();
                    if(isFirst) isFirst = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition4a = reentrantLock.newCondition();
        Condition condition4b = reentrantLock.newCondition();
        Condition condition4c = reentrantLock.newCondition();
        Outputer taskA = new Outputer('A',reentrantLock,condition4a,condition4b);
        taskA.setFirst(true);
        Thread a = new Thread(taskA,"thread-a");
        Thread b = new Thread(new Outputer('B',reentrantLock,condition4b,condition4c),"thread-b");
        Thread c = new Thread(new Outputer('C',reentrantLock,condition4c,condition4a),"thread-c");

        a.start();
        Thread.sleep(100);
        b.start();
        Thread.sleep(100);
        c.start();
    }
}
