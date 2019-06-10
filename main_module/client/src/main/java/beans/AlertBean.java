package beans;

import jms.Event;
import jms.EventType;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "AlertBean")
@SessionScoped
public class AlertBean {


    private final String path = "http://127.0.0.1:8080/implementation/api/rest";

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
        return client.getAll();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "alert.xhtml";
    }

}
