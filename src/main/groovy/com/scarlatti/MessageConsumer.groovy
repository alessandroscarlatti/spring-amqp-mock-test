package com.scarlatti

import groovy.util.logging.Slf4j
import org.springframework.amqp.core.MessageBuilder
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.amqp.core.Message
import org.springframework.stereotype.Component

/**
 *  ___               _    ___             _     _      10/7/2017
 *  ))_ __  ___  _ _  )L   ))_) __  ___  __)) __ ))_  ___ __ _ _
 * ((_((|  ((_( ((\( ((   ((__)(|  ((_( ((_( _))((`( ((_( \(((/'
 */
@Component
@Slf4j
class MessageConsumer {

    @Autowired
    RabbitTemplate rabbitTemplate

    @RabbitListener(queues = '${rabbit.listen.queue}')
    void receiveMessage(Message message) {
        log.info("Received message: ${message}")
        log.info("with payload: ${new String(message.body)}")
        log.info("with headers: ${message.messageProperties.headers}")

        if (message.body.length == 0) {
            log.info("sending message")

            Message newMessage = MessageBuilder.withBody("hello!".bytes).build()

            log.info("with payload: ${new String(newMessage.body)}")
            log.info("with headers: ${newMessage.messageProperties.headers}")

            rabbitTemplate.send(newMessage)
        }
    }
}
