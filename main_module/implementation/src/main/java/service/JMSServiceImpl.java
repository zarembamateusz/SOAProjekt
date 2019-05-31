package service;


import jms.Event;
import jms.service.JMSService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Remote(JMSService.class)
@Stateless
public class JMSServiceImpl implements JMSService{

    @Resource(mappedName = "java:jboss/exported/jms/topic/test")
    private Topic topic;

    @Inject
    private JMSContext jmsContext;

    @Override
    public void sendTopic(Event event) {
        jmsContext.createProducer().send(topic,event);
    }
}
