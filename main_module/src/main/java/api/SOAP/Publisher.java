package api.SOAP;

import javax.xml.ws.Endpoint;

public class Publisher {
    public Publisher()
    {
        Endpoint.publish("http://localhost:8080/ServiceSOAP", new ServiceSOAPlmplementation());
    }
}



