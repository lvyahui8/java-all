package org.lyh.spring.base.boot.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class Application {

    public static final String STRING_TOPIC = "chat";
    public static final String OBJECT_TOPIC = "object";

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter,MessageListenerAdapter objMessageListenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(STRING_TOPIC));
        container.addMessageListener(objMessageListenerAdapter,new PatternTopic(OBJECT_TOPIC));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    MessageListenerAdapter objMessageListenerAdapter(){
        return new MessageListenerAdapter(new ObjectReceiver(),"receiveObject");
    }

    // @Bean
    // StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
    //     return new StringRedisTemplate(connectionFactory);
    // }


    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws InterruptedException {

        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        RedisTemplate<String,Object> objectRedisTemplate = (RedisTemplate<String, Object>) ctx.getBean("objectRedisTemplate");
        CountDownLatch latch = ctx.getBean(CountDownLatch.class);

        LOGGER.info("Sending message...");
        template.convertAndSend(STRING_TOPIC, "Hello from Redis!");

        // 等待Receiver线程收到消息
        latch.await();


        LOGGER.info("Sending object...");
        objectRedisTemplate.convertAndSend(OBJECT_TOPIC,520);

        System.exit(0);
    }
}