package API.SOAP;

import Models.User;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "API.SOAP.ServiceSOAP")
public class ServiceSOAPlmplementation implements ServiceSOAP {

    @Inject
    private User userTest;

    @WebMethod
    public User getUser(int id) {
        return null;
    }
    @WebMethod
    public User updateUser(int id, String name) {
        return null;
    }
    @WebMethod
    public boolean deleteUser(int id) {
        return false;
    }
    @WebMethod
    public User addUser(int id, String name) {
        return null;
    }
}
