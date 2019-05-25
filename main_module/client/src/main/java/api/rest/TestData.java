package api.rest;


import models.CarPlace;
import models.Zone;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TestData {

    public static List<Zone> zoneList = new ArrayList<Zone>() {{
        add(Zone.builder()
                .places(carPlaces(15))
                .id(UUID.randomUUID().toString())
                .workers(workers(15))
                .build());
        add(Zone.builder()
                .places(carPlaces(113))
                .id(UUID.randomUUID().toString())
                .workers(workers(155))
                .build());
        add(Zone.builder()
                .places(carPlaces(32))
                .id(UUID.randomUUID().toString())
                .workers(workers(1))
                .build());
        add(Zone.builder()
                .places(carPlaces(12))
                .id(UUID.randomUUID().toString())
                .workers(workers(2))
                .build());
    }};

    private static Set<CarPlace> carPlaces(final int number) {
        return Stream.iterate(0, i -> i + 1).limit(number)
                .map(i -> CarPlace.builder()
                        .id(UUID.randomUUID()
                                .toString())
                        .build()
                ).collect(Collectors.toSet());
    }

    private static Set<String> workers(final int number) {
        return Stream.iterate(0, i -> i + 1).limit(number)
                .map(i -> UUID.randomUUID()
                                .toString()
                ).collect(Collectors.toSet());
    }
}
