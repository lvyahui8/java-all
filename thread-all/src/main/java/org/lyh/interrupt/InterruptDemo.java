package org.lyh.interrupt;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author feego lvyahui8@gmail.com
 * @date 2020/9/12
 */
public class InterruptDemo {
    public static class Worker extends Thread {
        boolean interrupted;

        public void interrupt1() {
            interrupted = true;
        }

        public boolean isInterrupted1(boolean cleanInterrupted) {
            if (interrupted) {
                if (cleanInterrupted) {
                    interrupted = false;
                }
                return true;
            }
            return false;
        }

        public static boolean interrupted1() {
            return ((Worker) currentThread()).isInterrupted1(true);
        }

        @Override
        public void run() {
            while(true) {
                if (interrupted1()) {
                    System.out.println("interrupted, so exit!");
                    break;
                }
                System.out.println("do something...");

            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.start();
        Thread.sleep(1000);
        worker.interrupt1();
    }
}
