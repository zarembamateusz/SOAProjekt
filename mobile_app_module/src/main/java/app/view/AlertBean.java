package app.view;

import jms.Event;
import jms.EventType;
import models.service.EventService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "AlertBean")
@SessionScoped
public class AlertBean {


    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/EventServiceImpl!models.service.EventService")
    private EventService eventService;
    private List<Event> eventList = new ArrayList<>();
    private List<Event> filtredEventList = new ArrayList<>();
    private List<EventType> eventTypes =new ArrayList<EventType>(Arrays.asList(EventType.values()));


    public List<EventType> getEventTypes() {
        return eventTypes;
    }


    public List<Event> getFiltredEventList() {
        return filtredEventList;
    }

    public void setFiltredEventList(List<Event> filtredEventList) {
        this.filtredEventList = filtredEventList;
    }


    public List<Event> getEventList() {
        // tylko do testów
        return eventService.getAll();
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

}
