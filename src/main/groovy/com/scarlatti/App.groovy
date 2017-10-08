package com.scarlatti

import groovy.util.logging.Slf4j
import org.springframework.amqp.core.MessageBuilder
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired

/**
 / o-o                 o      o--o             o     o        10/7/2017
 * o                   |      |   |            |     |
 * |  -o o-o  oo o-o  -o-     O--o  o-o  oo  o-O o-o O--o  oo o   o   o
 * o   | |   | | |  |  |      |   | |   | | |  |  \  |  | | |  \ / \ /
 *  o-o  o   o-o-o  o  o      o--o  o   o-o- o-o o-o o  o o-o-  o   o
 */
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
@Slf4j
class App implements CommandLineRunner {

    static void main(String[] args) {
        SpringApplication.run(App.class, args)
    }

    @Autowired
    RabbitTemplate rabbitTemplate

    @Override
    void run(String... args) throws Exception {

    }
}