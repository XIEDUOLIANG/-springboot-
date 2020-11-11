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

    public static void main(String[] args) throws InterruptedException{

        //The main() method kicks off everything by creating a Spring application context
        ApplicationContext ctx = SpringApplication
                .run(StartwithofficialdocumentationApplication.class, args);

        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        Receiver receiver = ctx.getBean(Receiver.class);

        /*for (int i = 0; i < 1000; i++) {
            new Task(template).start();
        }
        Thread.sleep(500L);
        System.out.println(receiver.getCount());
        System.out.println(receiver.getCounter2());
        System.out.println(Task.num);*/
    }

    /*static class Task extends Thread{

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
    }*/

}
