package com.example.startwithofficialdocumentation;

import com.example.startwithofficialdocumentation.redis.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
@Slf4j
public class StartwithofficialdocumentationApplication {

    /**
     * The connection factory and message listener container beans
     * are all you need to listen for messages
     * */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter adapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(adapter, new PatternTopic("chat"));

        return container;
    }

    /**
     * The bean defined in the listenerAdapter method
     * is registered as a message listener in the message listener container defined in container
     * and will listen for messages on the chat topic.
     * */
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }

    /**
     * the Receiver class is a POJO, 
     * it needs to be wrapped in a message listener adapter that implements the MessageListener interface
     *
     * The message listener adapter is also configured to call
     * the receiveMessage() method on Receiver when a message arrives.
     * */
    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    /**
     * To send a message, you also need a Redis template
     * Here, it is a bean configured as a StringRedisTemplate , an implementation of RedisTemplate
     * that is focused on the common use of Redis, where both keys and values are String instances
     * */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    public static void main(String[] args) throws InterruptedException{

        //The main() method kicks off everything by creating a Spring application context
        ApplicationContext ctx = SpringApplication
                .run(StartwithofficialdocumentationApplication.class, args);

        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        Receiver receiver = ctx.getBean(Receiver.class);

        for (int i = 0; i < 1000; i++) {
            new Task(template).start();
        }
        Thread.sleep(500L);
        System.out.println(receiver.getCount());
        System.out.println(receiver.getCounter2());
        System.out.println(Task.num);
    }

    static class Task extends Thread{

        private StringRedisTemplate stringRedisTemplate;
        private static Integer num = 0;

        public Task(StringRedisTemplate stringRedisTemplate) {
            this.stringRedisTemplate = stringRedisTemplate;
        }

        @Override
        public void run() {
            log.info("Send Message..");
            stringRedisTemplate.convertAndSend("chat","hello from redis");
            num = num + 1;
        }
    }

}
