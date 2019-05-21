package API.SOAP;
import Models.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ServiceSOAP {
    @WebMethod
    User getUser(int id);

    @WebMethod
    User updateUser(int id, String name);

    @WebMethod
    boolean deleteUser(int id);

    @WebMethod
    User addUser(int id, String name);
}
