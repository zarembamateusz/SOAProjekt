package service;


import jms.Event;
import jms.service.JMSService;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Remote(JMSService.class)
@Stateless
@PermitAll
@SecurityDomain("test-policy")
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
