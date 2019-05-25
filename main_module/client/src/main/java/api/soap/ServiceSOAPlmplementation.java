package api.soap;


import models.User;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "api.soap.ServiceSOAP")
public class ServiceSOAPlmplementation implements ServiceSOAP {


    @WebMethod
    public User getUser(String id) {
        return null;
    }
    @WebMethod
    public User updateUser(String id, String name) {
        return null;
    }
    @WebMethod
    public boolean deleteUser(String id) {
        return false;
    }
    @WebMethod
    public User addUser(String id, String name) {
        return null;
    }
}
