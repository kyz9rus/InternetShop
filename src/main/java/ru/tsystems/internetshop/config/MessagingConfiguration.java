package ru.tsystems.internetshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This is class contains configuration for the queue in embedded wildfly queue
 */
@Configuration
public class MessagingConfiguration {

    private static final String QUEUE_NAME = "top10Products";

    /**
     * This is method configure embedded activeMq connection factory
     *
     * @return turned in connection factory bean
     */
    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        return InitialContext.doLookup("java:jboss/DefaultJMSConnectionFactory");
    }

    /**
     * This is method configure jms template
     *
     * @return turned in jms template bean with
     */
    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(QUEUE_NAME);
        return template;
    }

}
