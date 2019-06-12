package parkometer.client;

import lombok.NoArgsConstructor;
import lombok.val;
import models.CarPlace;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.ws.rs.core.UriBuilder;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.out;

@NoArgsConstructor(staticName = "create")
public class Cmd {
    private final String path = "http://127.0.0.1:8080/implementation/api/rest";

    private final RestService client = new ResteasyClientBuilder().build()
            .target(UriBuilder.fromPath(path))
            .proxy(RestService.class);
    private final Scanner scanner = new Scanner(System.in);

    private static Map<Integer, Integer> map;

    static {
        map = new HashMap<>();
        map.put(1, 30);
        map.put(2, 1);
        map.put(3, 2);
        map.put(4, 3);
        map.put(5, 4);
        map.put(6, 5);
        map.put(7, 6);
        map.put(8, 7);
    }

    public void showStart() {
        out.println("Witamy w smart parkingu");
        out.println("Wybierz swoją zone");
        val zones = client.getAllZones();
        zones.forEach(z -> out.println("\t -  " + z.getCode()));
        val code = scanner.next();
        out.println("Wybrano " + code);

        out.println("Wybierz miejsce");
        val zone = zones.stream()
                .filter(z -> z.getCode().equals(code))
                .findFirst()
                .get();

        val mapPlace = zone.getPlaces()
                .stream()
                .peek(z -> out.println("\t - " + z.getCode()))
                .collect(Collectors.toMap(CarPlace::getCode, CarPlace::getId));
        val carcode = scanner.next();
        val placeId = mapPlace.get(carcode);
        out.println("Wybierz czas");
        map.forEach((key, value) -> out.println(key + " -> " + value));
        val time = map.get(scanner.nextInt());
        //do ent time trzeba dodac jeszcze 5min bo bedziemy
        val endTime = time ==1 ? ZonedDateTime.now().plusSeconds(time+45) : ZonedDateTime.now().plusMinutes(time);

        client.putNewTicket(zone.getId(), placeId, endTime.toLocalDateTime().toString());

    }

}
