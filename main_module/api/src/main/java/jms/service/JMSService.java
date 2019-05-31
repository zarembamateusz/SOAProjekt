package jms.service;

import jms.Event;

public interface JMSService {
    void sendTopic(Event event);
}
