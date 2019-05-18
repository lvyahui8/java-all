package org.lyh.mq;

import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 *
 */
public class MqApp
{
    public static void main( String[] args ) {
        SimpleConsumer consumer = null;
        SimpleProducer producer = null;
        boolean flag = true;
        while(flag){
            // 断线重连
            try {
                String queueName = "simpleQueue";
                consumer = new SimpleConsumer(queueName);
                producer = new SimpleProducer(queueName);
                Thread consumerThread = new Thread(consumer);
                consumerThread.start();
                for (int i = 0; i < 1000; i++) {
                    HashMap<String,Integer> message = new HashMap<>();
                    message.put("message number", i);
                    Thread.sleep(500);
                    producer.sendMessage(message);
                    System.out.println("Message Number "+ i +" sent.");
                }
            } catch (IOException | TimeoutException | InterruptedException | ShutdownSignalException e) {
                // if(producer != null && consumer != null){
                //     try {
                //         consumer.connect();
                //         producer.connect();
                //     } catch (IOException | TimeoutException e1) {
                //         e1.printStackTrace();
                //     }
                // }
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
}
