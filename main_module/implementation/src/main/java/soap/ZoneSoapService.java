package soap;

import jms.Event;
import models.Zone;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ZoneSoapService {

    @WebMethod
    List<Zone> getAllZones();

    @WebMethod
    void action(Event event);

    Zone getZoneByCode(String code);
}
