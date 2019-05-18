package org.lyh.questions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Question03_01 {
    private static class ThingBuffer{
        private static final int DEFAULT_SIZE = 100;
        private Object [] items = new Object[DEFAULT_SIZE];
        private int pTake,pPut,count;

        private final ReentrantLock reentrantLock = new ReentrantLock();
        private final Condition takeCondtion = reentrantLock.newCondition();
        private final Condition putCondition = reentrantLock.newCondition();


        private void put(Object item) throws InterruptedException {
            reentrantLock.lock();
            try{
                while(count >= DEFAULT_SIZE){
                    putCondition.await();
                }
                items[pPut] = Thread.currentThread().getName() + "Product_" + item;
                System.out.println("thread " + Thread.currentThread().getName() + " produce " + item);
                Thread.sleep(60);
                pPut = (pPut + 1) % DEFAULT_SIZE;
                count ++;
                takeCondtion.signal();
            } finally {
                reentrantLock.unlock();
            }
        }

        private Object take() throws InterruptedException {
            reentrantLock.lock();
            try{
                while(count == 0){
                    takeCondtion.await();
                }
                Object item = items[pTake];
                System.out.println("thread " + Thread.currentThread().getName() + " consume " + item);
                Thread.sleep(60);
                pTake = (pTake + 1) % DEFAULT_SIZE;
                count--;
                putCondition.signal();
                return item;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    private static abstract class ThingHandler extends Thread{
        public static final int DEFAULT_THING_COUNT = 300;

        ThingBuffer thingBuffer;


        private ThingHandler(ThingBuffer thingBuffer) {
            this.thingBuffer = thingBuffer;
        }
    }

    private static class Producer extends ThingHandler{
        private Producer(ThingBuffer thingBuffer) {
            super(thingBuffer);
        }

        public void run() {
            for (int i = 0 ; i < DEFAULT_THING_COUNT; i ++){
                try {
                    thingBuffer.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer extends ThingHandler{

        private Consumer(ThingBuffer thingBuffer) {
            super(thingBuffer);
        }

        public void run() {
            for (int i = 0 ; i < DEFAULT_THING_COUNT; i ++){
                try {
                    thingBuffer.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        ThingBuffer thingBuffer = new ThingBuffer();
        Producer producer1 = new Producer(thingBuffer);
        Producer producer2 = new Producer(thingBuffer);
        Consumer consumer1 = new Consumer(thingBuffer);
        Consumer consumer2 = new Consumer(thingBuffer);

        producer1.start();
        producer2.start();

        consumer1.start();
        consumer2.start();
    }
}
