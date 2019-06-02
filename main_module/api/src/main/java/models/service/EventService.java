package models.service;

import jms.Event;

import java.util.List;

public interface EventService {
    List<Event> findAllUsersEvent(String userId);
    List<Event> findAllUnreadeEvent(String userId);
    void carIn(Event event);
    void carOut(Event event);

}
