package org.lyh.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class EndPoint {

    protected Channel channel;
    protected Connection connection;
    protected String queueName;

    public EndPoint(String queueName) throws IOException, TimeoutException {
        this.queueName = queueName;
        connect();
    }

    public void connect() throws IOException, TimeoutException{
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("10.244.178.190");
        factory.setUsername("tencent_zhiyun");
        factory.setPassword("admin888");

        connection = factory.newConnection();

        channel = connection.createChannel();

        channel.queueDeclare(this.queueName, false, false, false, null);
    }

    /**
     * 关闭channel和connection。并非必须，因为隐含是自动调用的。
     *
     * @throws IOException
     */
    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }


}