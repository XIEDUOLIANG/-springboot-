package com.example.startwithofficialdocumentation.redis;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author XieDuoLiang
 * @date 2020/11/11 下午2:35
 */
@Slf4j
public class Receiver {

    /**
     * In any messaging-based application,
     * there are message publishers and messaging receivers.
     * To create the message receiver, implement a receiver with a method to respond to messages.
     *
     * The Receiver is a POJO that defines a method for receiving messages.
     * When you register the Receiver as a message listener,
     * you can name the message-handling method whatever you want
     * */

    private AtomicInteger counter = new AtomicInteger();
    private int counter2 = 0;

    /**
     * 验证了似乎是一个线程在接受消息，没必要用 AtomicInteger
     * */
    public void receiveMessage(String message) {
        log.info("Receive <" + message + ">");
        counter.incrementAndGet();
        counter2 = counter2 + 1;
    }

    public int getCount() {
        return counter.get();
    }

    public int getCounter2() {
        return counter2;
    }
}
