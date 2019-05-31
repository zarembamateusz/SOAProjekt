package jms;

import com.sun.xml.ws.developer.Stateful;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import java.io.Serializable;

@Stateful
public class TopicProducer implements Serializable {
    @Resource(mappedName = "java:/queue/test")
    private Topic topic;
    @Inject
    private JMSContext jmsContext;

    public void enqueue(final Event event) {
        jmsContext.createProducer().send(topic, event);
    }
}