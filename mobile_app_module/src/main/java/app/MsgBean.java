package app;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import jms.Event;
import jms.EventType;
import lombok.val;
import models.service.EventService;
import models.service.UserService;
import models.service.ZoneService;

@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/topic/SOA_Test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability",
                propertyValue = "Durable")})
public class MsgBean implements MessageListener {


    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/EventServiceImpl!models.service.EventService")
    private EventService eventService;
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/UserServiceImpl!models.service.UserService")
    private UserService userService;

    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                val event = (Event) ((ObjectMessage) message).getObject();
                if (event.getType().equals(EventType.CAR_IN)) eventService.carIn(event);
                else eventService.carOut(event);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
