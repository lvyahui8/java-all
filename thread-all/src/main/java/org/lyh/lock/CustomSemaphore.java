package org.lyh.lock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2020/2/13 0:19
 */
public class CustomSemaphore {
    private Sync sync;

    class Sync extends AbstractQueuedSynchronizer {


        public Sync(int c) {
            setState(c);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            int state = getState();
            int rem = state - 1;
            if (rem < 0 || compareAndSetState(state,rem)) {
                return rem;
            }
            return 0;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            if (state <= 0) {
                return false;
            }
            return compareAndSetState(state,state - 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return true;
        }
    }

    public CustomSemaphore(int c) {
        this.sync = new Sync(c);
    }

    public void acquire() {
        sync.acquire(1);
    }

    public void release () {
        sync.release(1);
    }

    public static void main(String[] args) {
        final CustomSemaphore customSemaphore = new CustomSemaphore(10);

        Executor executor = Executors.newFixedThreadPool(100);

        for (int i = 0 ; i < 10000; i ++) {
            executor.execute(() -> {
                try {
                    customSemaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("hh");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    customSemaphore.release();
                }
            });
        }
    }
}
