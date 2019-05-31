package service;


import jms.Event;
import jms.TopicProducer;
import jms.service.JMSService;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(JMSService.class)
@Stateless
public class JMSServiceImpl implements JMSService{

    @EJB
    private TopicProducer topicProducer;

    @Override
    public void sendTopic(Event event) {
        topicProducer = new TopicProducer();
        topicProducer.enqueue(event);
    }
}
