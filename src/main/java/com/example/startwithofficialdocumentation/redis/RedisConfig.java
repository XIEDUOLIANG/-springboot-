package com.example.startwithofficialdocumentation.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author XieDuoLiang
 * @date 2020/11/11 下午3:14
 */
@Component
@Slf4j
public class RedisConfig {

    /**
     * The connection factory and message listener container beans
     * are all you need to listen for messages
     * */
    @Bean
    RedisMessageListenerContainer container(LettuceConnectionFactory connectionFactory,
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
    StringRedisTemplate template(LettuceConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
