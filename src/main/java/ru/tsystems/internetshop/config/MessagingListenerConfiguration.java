package ru.tsystems.internetshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * This is class contains configuration for JMS listener
 */
@Configuration
@EnableJms
public class MessagingListenerConfiguration {

    @Autowired
    ConnectionFactory connectionFactory;

    /**
     * This is method configure jms listener container factory
     *
     * @return turned in jms listener container factory bean
     */
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-1");
        return factory;
    }
}
