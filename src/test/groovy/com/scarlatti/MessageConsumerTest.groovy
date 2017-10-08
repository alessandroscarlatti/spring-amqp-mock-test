package com.scarlatti

import org.junit.Test
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.core.MessagePropertiesBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MessageConsumerTest extends Specification {

    @Autowired
    MessageConsumer messageConsumer

    @Test
    void checkHeaders() {

        setup:
            byte[] bytes = "asdf".getBytes()
            MessageProperties props = MessagePropertiesBuilder.newInstance()
                    .setHeader("x-dead-letter-exchange", "SquawksExchangeError")
                    .build()

            Message message = new Message(bytes, props)

        when:
            messageConsumer.receiveMessage(message)

        then:
            notThrown(Exception)
    }

}