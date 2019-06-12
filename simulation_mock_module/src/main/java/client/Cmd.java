package client;


import lombok.val;
import api.soap.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Cmd {
    URL url = new URL("http://localhost:8080/implementation/ZoneSoapServiceImpl?wsdl");
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
                        .filter(z -> code.equals(z.getCode()))
                        .findFirst()
                        .orElseThrow(RuntimeException::new);
                out.println("Wybierz miejsce");

                val mapPlace = zone.getPlaces()
                        .stream()
                        .peek(z -> out.println("\t - " + z.getCode()))
                        .collect(Collectors.toMap(CarPlace::getCode, CarPlace::getId));
                val carcode = scanner.next();
                val placeId = mapPlace.get(carcode);
                out.println("Wybierz akcje");
                out.println("1 -> przyjechał samochód");
                out.println("2 -> odjechał samochód");
                val actionNum = scanner.nextInt();
                val event = createEvent(actionNum, placeId, carcode, zone.getId(),zone.getCode());
                service.action(event);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private Event createEvent(final int actionNum, final String carPlaceId, final String carCode,
                              final String zoneId,
                              final String zoneCode) {
        val event = new Event();
        event.setZoneId(zoneId);
        event.setCarPlaceId(carPlaceId);
        event.setCarCode(carCode);
        event.setZoneCode(zoneCode);
        event.setType(actionNum == 1 ? EventType.CAR_IN : EventType.CAR_OUT);
        return event;
    }

}
