package api.soap;

import models.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ServiceSOAP {
    @WebMethod
    User getUser(String  string);

    @WebMethod
    User updateUser(String id, String name);

    @WebMethod
    boolean deleteUser(String id);

    @WebMethod
    User addUser(String id, String name);
}
