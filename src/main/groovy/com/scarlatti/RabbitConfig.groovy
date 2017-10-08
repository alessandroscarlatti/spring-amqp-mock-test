package com.scarlatti

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 /~     ,_|- |)     | _|_
 \_||`(||||_ |)|`(|(|_\||(|LL|
 * 10/07/2017
 *
 */
@Configuration
@EnableRabbit
class RabbitConfig {


    @Bean
    @ConfigurationProperties("rabbit.connection")
    ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory()
    }

    @Bean
    @ConfigurationProperties("rabbit.publish")
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory)
        return rabbitTemplate
    }

    @Bean
    SimpleMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer()
        container.setConnectionFactory(connectionFactory)
        return container
    }
}
