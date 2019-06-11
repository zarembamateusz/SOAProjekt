package beans;

import jms.Event;
import jms.EventType;
import jms.service.Observer;
import lombok.val;
import models.User;
import models.service.EventService;
import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.primefaces.context.PrimeFacesContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ManagedBean(name = "AlertBean")
@SessionScoped
public class AlertBean{


    private final String path = "http://127.0.0.1:8080/implementation/api/rest/";
    private final static Logger loger = Logger.getLogger(AlertBean.class);
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

    private User user;


    @PostConstruct
    public void init() {
        val login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        user = client.getUserByLogin(login);
    }


    public List<Event> getEventList() {
        // tylko do testów
        return client.getAllClientEvents(user.getId());
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "alert.xhtml";
    }

}
