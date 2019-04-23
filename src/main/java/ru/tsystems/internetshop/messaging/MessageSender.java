package ru.tsystems.internetshop.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import javax.jms.Message;
import javax.jms.ObjectMessage;

@Component
public class MessageSender {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final String message) {
        System.out.println("Sending message... ");
        jmsTemplate.send(session -> session.createTextMessage(message));
    }

}