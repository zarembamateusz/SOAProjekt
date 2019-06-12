import api.soap.ZoneSoapService;
import api.soap.ZoneSoapServiceImplService;
import client.Cmd;
import lombok.val;

import java.net.MalformedURLException;
import java.net.URL;

public class Simulation {



    public Simulation() throws MalformedURLException {

    }

    public static void main(String[] args) throws MalformedURLException {
        new Cmd().start();
    }

}
