
package api.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the api.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllZones_QNAME = new QName("http://soap.api/", "getAllZones");
    private final static QName _Action_QNAME = new QName("http://soap.api/", "action");
    private final static QName _GetAllZonesResponse_QNAME = new QName("http://soap.api/", "getAllZonesResponse");
    private final static QName _ActionResponse_QNAME = new QName("http://soap.api/", "actionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: api.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ActionResponse }
     * 
     */
    public ActionResponse createActionResponse() {
        return new ActionResponse();
    }

    /**
     * Create an instance of {@link Action }
     * 
     */
    public Action createAction() {
        return new Action();
    }

    /**
     * Create an instance of {@link GetAllZonesResponse }
     * 
     */
    public GetAllZonesResponse createGetAllZonesResponse() {
        return new GetAllZonesResponse();
    }

    /**
     * Create an instance of {@link GetAllZones }
     * 
     */
    public GetAllZones createGetAllZones() {
        return new GetAllZones();
    }

    /**
     * Create an instance of {@link CarPlace }
     * 
     */
    public CarPlace createCarPlace() {
        return new CarPlace();
    }

    /**
     * Create an instance of {@link LocalDateTime }
     * 
     */
    public LocalDateTime createLocalDateTime() {
        return new LocalDateTime();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
    }

    /**
     * Create an instance of {@link Zone }
     * 
     */
    public Zone createZone() {
        return new Zone();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllZones }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.api/", name = "getAllZones")
    public JAXBElement<GetAllZones> createGetAllZones(GetAllZones value) {
        return new JAXBElement<GetAllZones>(_GetAllZones_QNAME, GetAllZones.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Action }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.api/", name = "action")
    public JAXBElement<Action> createAction(Action value) {
        return new JAXBElement<Action>(_Action_QNAME, Action.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllZonesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.api/", name = "getAllZonesResponse")
    public JAXBElement<GetAllZonesResponse> createGetAllZonesResponse(GetAllZonesResponse value) {
        return new JAXBElement<GetAllZonesResponse>(_GetAllZonesResponse_QNAME, GetAllZonesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.api/", name = "actionResponse")
    public JAXBElement<ActionResponse> createActionResponse(ActionResponse value) {
        return new JAXBElement<ActionResponse>(_ActionResponse_QNAME, ActionResponse.class, null, value);
    }

}
