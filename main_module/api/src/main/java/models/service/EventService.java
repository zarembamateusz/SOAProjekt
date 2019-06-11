package models.service;

import jms.Event;
import jms.service.Observer;

import java.util.List;

public interface EventService {
    List<Event> findAllUsersEvent(String userId);
    List<Event> getAll();
    List<Event> findAllUnreadeEvent(String userId);
    void notifyObservers(Event event);
    void carIn(Event event);
    void carOut(Event event);

}
