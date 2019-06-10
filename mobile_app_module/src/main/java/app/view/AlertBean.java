package app.view;

import jms.Event;
import jms.EventType;
import models.service.EventService;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "AlertBean")
@SessionScoped
public class AlertBean {


    private final String path = "http://127.0.0.1:8080/api/rest";

    private final RestService client = new ResteasyClientBuilder().build()
            .target(UriBuilder.fromPath(path))
            .proxy(RestService.class);
    private List<Event> filtredEventList = new ArrayList<>();
    private List<EventType> eventTypes = new ArrayList<EventType>(Arrays.asList(EventType.values()));


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
        return client.getAllClientEvents("1");
    }

}
