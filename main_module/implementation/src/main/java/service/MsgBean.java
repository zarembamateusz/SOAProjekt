package service;

import jms.Event;
import jms.EventType;
import lombok.val;
import models.service.EventService;
import models.service.UserService;
import models.service.ZoneService;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.stream.Stream;

@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/topic/test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability",
                propertyValue = "durable")})
@PermitAll
@SecurityDomain("test-policy")
public class MsgBean implements MessageListener {

    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/EventServiceImpl!models.service.EventService")
    private EventService eventService;

    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                val event = (Event) ((ObjectMessage) message).getObject();
                eventService.notifyObservers(event);
                if (event.getType().equals(EventType.CAR_IN)) eventService.carIn(event);
                else eventService.carOut(event);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
