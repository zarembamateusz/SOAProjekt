package parkometer.client;

import lombok.NoArgsConstructor;
import lombok.val;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.ws.rs.core.UriBuilder;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        zone.getPlaces()
                .forEach(z -> out.println("\t - " + z.getId()));
        val placeId = scanner.next();

        out.println("Wybierz czas");
        map.forEach((key, value) -> out.println(key + " -> " + value));
        val time = map.get(scanner.nextInt());
        val endTime = ZonedDateTime.now().plusSeconds(time);

        client.putNewTicket(zone.getId(), placeId, endTime.toLocalDateTime().toString());

    }

}
