package com.foo.jms.simple.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SimpleSubscriberOne {

    @JmsListener(destination = "${com.foo.ibmmq.simple.topic}")
    public void process(Message<String> message) {
        log.info("Headers :\n{} \n",message.getHeaders());
        log.info("Payload :\n{} \n",message.getPayload());
    }
}
