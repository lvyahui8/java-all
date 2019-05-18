package org.lyh.camel;

import org.claret.utils.NetUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/7 22:05
 */
public class ApiPlatformTest {
//    @Test
//    public void testConcurrent(){
    public static void main(String[] args) {
        int n = 10;
        ExecutorService threadPool = Executors.newFixedThreadPool(n);
        final CyclicBarrier cb = new CyclicBarrier(n);

        for (int i = 0 ;i < n * 10; i++){
            final int taskId = i;
            threadPool.execute(new Runnable() {
                public void run() {
                    try {
                        cb.await();
                        String resp = NetUtils.get("http://127.0.0.1:8088/test/app/list");
                        System.out.println(Thread.currentThread().getName() + " run taskId:" + taskId + " resp:" +resp);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
    }
}
