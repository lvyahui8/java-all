package org.lyh.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/10/11 19:46
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        int n = 2;
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(n);
        ExecutorService service = Executors.newFixedThreadPool(n);
        Random r = new Random();
        for (int i = 0;i < n;i++){
            service.submit(new Runnable() {
                public void run() {
                    try {
                        while(true){
                            String msg = queue.take();
                            System.out.println(msg);
                            Thread.sleep(4000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        while(true){
            try {
                queue.put("String-"+r.nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
