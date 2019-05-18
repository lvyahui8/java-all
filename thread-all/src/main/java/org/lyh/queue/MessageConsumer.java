package org.lyh.queue;

import java.util.Queue;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/9/22 20:22
 */
public class MessageConsumer extends SimpleThread {
    private Queue<String> queue;

    public MessageConsumer(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            String last ;
            while((last = queue.poll()) == null){
                sleepCurrentThread(100);
            }
            // 消费
            sleepCurrentThread(50);
            System.out.println(Thread.currentThread().getName() + " used message " + last);
        }
    }
}
