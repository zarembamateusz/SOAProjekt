package beans;

import jms.Event;
import jms.EventType;
import lombok.val;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/topic/test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability",
                propertyValue = "durable")})
@PermitAll
@SecurityDomain("java:/jaas/test-policy")
public class EventListener implements MessageListener {
    @Inject
    public javax.enterprise.event.Event<String> events;

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                val event = (Event) ((ObjectMessage) message).getObject();
                events.fire(event.getZoneId());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
