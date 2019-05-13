package ru.tsystems.internetshop.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import ru.tsystems.avonshopnews.model.NewsInfo;

import javax.jms.JMSException;
import java.util.logging.Logger;

/**
 * This class is jms receiver for avonShopNews application
 */
@Component
public class MessageReceiver {

    @Autowired
    private ru.tsystems.internetshop.util.NewsInfo newsInfo;

    static final Logger fileLogger = Logger.getLogger("fileLogger");
    private static final String NEWS_QUEUE = "news";

    /**
     * This method receives message with NewsInfo object from AvonShopNews application
     *
     * @param message object jms message, which contains NewsInfo object
     */
    @JmsListener(destination = NEWS_QUEUE)
    public void receiveMessage(final Message<NewsInfo> message) throws JMSException {
        fileLogger.info("RECEIVE: " + message.getPayload() + " | " + message.getHeaders());

        MessageHeaders headers = message.getHeaders();
        fileLogger.info("Application : headers received : {}" + headers);

        NewsInfo newsInfo = message.getPayload();
        fileLogger.info("Application : response received : {}" + newsInfo);

        this.newsInfo.addNews(newsInfo);
    }
}
