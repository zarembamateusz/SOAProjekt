
package api.soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ZoneSoapServiceImplService", targetNamespace = "http://soap.api/", wsdlLocation = "http://localhost:8080/implementation/ZoneSoapServiceImpl?wsdl")
public class ZoneSoapServiceImplService
    extends Service
{

    private final static URL ZONESOAPSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException ZONESOAPSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName ZONESOAPSERVICEIMPLSERVICE_QNAME = new QName("http://soap.api/", "ZoneSoapServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/implementation/ZoneSoapServiceImpl?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ZONESOAPSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        ZONESOAPSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public ZoneSoapServiceImplService() {
        super(__getWsdlLocation(), ZONESOAPSERVICEIMPLSERVICE_QNAME);
    }

    public ZoneSoapServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ZONESOAPSERVICEIMPLSERVICE_QNAME, features);
    }

    public ZoneSoapServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, ZONESOAPSERVICEIMPLSERVICE_QNAME);
    }

    public ZoneSoapServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ZONESOAPSERVICEIMPLSERVICE_QNAME, features);
    }

    public ZoneSoapServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZoneSoapServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ZoneSoapService
     */
    @WebEndpoint(name = "ZoneSoapServiceImplPort")
    public ZoneSoapService getZoneSoapServiceImplPort() {
        return super.getPort(new QName("http://soap.api/", "ZoneSoapServiceImplPort"), ZoneSoapService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZoneSoapService
     */
    @WebEndpoint(name = "ZoneSoapServiceImplPort")
    public ZoneSoapService getZoneSoapServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.api/", "ZoneSoapServiceImplPort"), ZoneSoapService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ZONESOAPSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw ZONESOAPSERVICEIMPLSERVICE_EXCEPTION;
        }
        return ZONESOAPSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
