package ru.tsystems.internetshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Configuration
public class MessagingConfiguration {

    private static final String PRODUCTS_QUEUE = "top10Products";

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        return InitialContext.doLookup("java:jboss/DefaultJMSConnectionFactory");
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(PRODUCTS_QUEUE);
        return template;
    }

}
