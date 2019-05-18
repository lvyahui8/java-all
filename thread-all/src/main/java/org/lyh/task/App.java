package org.lyh.task;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        final ExecutorService requestThreadPool = Executors.newFixedThreadPool(10);

        Runnable requestTask = new Runnable() {
            public void run() {
                final Random random = new Random();
                for (int i = 0; i < 10 ; i++) {
                    Task task = new Task(random.nextInt(5));
                    try {
                        Thread.sleep(1003);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    TaskManager.submit(task);
                }
            }
        };

        for (int i = 0; i < 10 ;i ++) {
            if(!requestThreadPool.isShutdown()){
                requestThreadPool.submit(requestTask);
            }
        }

        requestThreadPool.shutdown();

    }
}
