package com.foo.jms.simple.queue.listener;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.TextMessage;
import java.time.Instant;

@Api(value="Send Message to a Queue", description = "Fire And Forget")
@RestController
@RequestMapping(path="/messages/simpleProducer")
@AllArgsConstructor
public class SimpleQueueProducer {

    @Value("${spring.jms.template.default-destination}")
    private String destinationName;

    @Autowired
    @Qualifier("queue")
    private JmsTemplate jmsTemplate;

    @PutMapping("/{message}")
    public void sentMessage(@PathVariable("message") String message) {
        jmsTemplate.send(destinationName, session -> {
            TextMessage payload = session.createTextMessage();
            payload.setText(message);
            return payload;
        });
    }

}
