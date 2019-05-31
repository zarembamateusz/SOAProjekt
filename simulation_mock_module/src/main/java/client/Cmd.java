package client;

import api.soap.*;
import lombok.val;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static java.lang.System.out;

public class Cmd {
    URL url = new URL("http://localhost:8080/ZoneSoapServiceImpl?wsdl");
    ZoneSoapService service = new ZoneSoapServiceImplService(url)
            .getZoneSoapServiceImplPort();

    private final Scanner scanner = new Scanner(System.in);

    public Cmd() throws MalformedURLException {
    }

    public void start() {
        while (true) {
            try {


                out.println("Witamy w smart parkingu");
                out.println("Wybierz swoją zone");
                val zones = service.getAllZones();
                zones.forEach(z -> out.println("\t -  " + z.getCode()));
                val code = scanner.next();
                out.println("Wybrano " + code);
                val zone = zones.stream()
                        .filter(z -> z.getCode().equals(code))
                        .findFirst()
                        .orElseThrow(RuntimeException::new);
                out.println("Wybierz miejsce");

                zone.getPlaces()
                        .forEach(z -> out.println("\t - " + z.getId()));
                val placeId = scanner.next();
                out.println("Wybierz akcje");
                out.println("1 -> przyjechał samochód");
                out.println("2 -> przyjechał samochód");
                val actionNum = scanner.nextInt();
                val event = createEvent(actionNum, placeId);
                service.action(event);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private Event createEvent(final int actionNum, final String carPlaceId) {
        val event = new Event();
        event.setCarPlaceId(carPlaceId);
        event.setType(actionNum == 1 ? EventType.CAR_IN : EventType.CAR_OUT);
        return event;
    }

}
