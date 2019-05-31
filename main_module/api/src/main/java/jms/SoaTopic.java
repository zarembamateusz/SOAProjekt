package jms;

import jms.Event;

public interface SoaTopic {
    void send(Event event);
}