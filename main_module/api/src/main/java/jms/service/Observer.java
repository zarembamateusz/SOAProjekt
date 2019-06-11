package jms.service;

import jms.Event;

public interface Observer {
    void action(Event event);
}
