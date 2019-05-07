package ru.tsystems.internetshop.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import ru.tsystems.avonshopnews.model.NewsInfo;

import javax.jms.JMSException;
import java.util.logging.Logger;

@Component
public class MessageReceiver {

    @Autowired
    private ru.tsystems.internetshop.util.NewsInfo newsInfo;

    static final Logger LOG = Logger.getLogger("logger");
    private static final String NEWS_QUEUE = "news";

    @JmsListener(destination = NEWS_QUEUE)
    public void receiveMessage(final Message<NewsInfo> message) throws JMSException {
        System.out.println("RECEIVE: " + message.getPayload() + " | " + message.getHeaders());
        MessageHeaders headers =  message.getHeaders();
        LOG.info("Application : headers received : {}" + headers);

        NewsInfo newsInfo = message.getPayload();
        LOG.info("Application : response received : {}" + newsInfo);


        this.newsInfo.addNews(newsInfo);
    }
}
