package ru.tsystems.internetshop.messaging;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * This class is jms sender for Storefront application
 */
@Component
public class MessageSender {

    @Autowired
    JmsTemplate jmsTemplate;

    private final Logger consoleLogger = Logger.getLogger("consoleLogger");
    private final Logger fileLogger = Logger.getLogger("fileLogger");

    /**
     * This method sends message about changing top products using jms template
     *
     * @param message string message
     */
    public void sendMessage(final String message) {
        consoleLogger.info("Sending message: " + message + "...");
        fileLogger.info("Sending message: " + message + "...");

        jmsTemplate.send(session -> session.createTextMessage(message));
    }

}