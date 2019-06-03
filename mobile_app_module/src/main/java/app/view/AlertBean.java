package app.view;

import jms.Event;
import jms.EventType;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "AlertBean")
@SessionScoped
public class AlertBean {


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
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

}
