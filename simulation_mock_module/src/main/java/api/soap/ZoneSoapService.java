
package api.soap;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ZoneSoapService", targetNamespace = "http://soap.api/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZoneSoapService {


    /**
     * 
     * @return
     *     returns java.util.List<api.soap.Zone>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllZones", targetNamespace = "http://soap.api/", className = "api.soap.GetAllZones")
    @ResponseWrapper(localName = "getAllZonesResponse", targetNamespace = "http://soap.api/", className = "api.soap.GetAllZonesResponse")
    public List<Zone> getAllZones();

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "action", targetNamespace = "http://soap.api/", className = "api.soap.Action")
    @ResponseWrapper(localName = "actionResponse", targetNamespace = "http://soap.api/", className = "api.soap.ActionResponse")
    public void action(
        @WebParam(name = "arg0", targetNamespace = "")
        Event arg0);

}