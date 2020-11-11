package com.example.startwithofficialdocumentation.common;

import com.example.startwithofficialdocumentation.StartwithofficialdocumentationApplication;
import com.example.startwithofficialdocumentation.redis.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author XieDuoLiang
 * @date 2020/11/11 下午5:32
 */
@Component
@Slf4j
@Order(100)
public class RedisMessageTestRunner implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private Receiver receiver;

    @Override
    public void run(String... args) throws Exception {
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
