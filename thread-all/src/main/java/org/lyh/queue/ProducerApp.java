package org.lyh.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 自适应容量产生消息，生产者视消息队列大小动态调整生产能力（为了消费者逻辑尽量简单，不用消费者触发生产者生产的方式）
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/9/22 20:18
 */
public class ProducerApp {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        MessageProducer producer = new MessageProducer(queue);

        for (int i = 0;i < 10; i++){
            MessageConsumer consumer = new MessageConsumer(queue);
            consumer.start();
        }

        producer.start();
    }
}
