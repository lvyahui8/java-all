package org.lyh.queue;

import java.util.Queue;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/9/22 20:20
 */
public class MessageProducer extends SimpleThread{

    private Queue<String> queue;

    private int current ;

    public MessageProducer(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            // 休眠较长时间
            produce(10000);
            sleepCurrentThread(500);
        }
    }

    protected void produce(int n){
        for (int i = current ;i < n; i++){
            queue.offer(String.valueOf(i));
        }
        current += n;
        if(current > 1000000){
            current = 0;
        }
    }
}
