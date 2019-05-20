package rest;


import models.Zone;

import java.util.ArrayList;
import java.util.List;


public class TestData {

    public static List<Zone> zoneList = new ArrayList<Zone>(){{
        add(new Zone(1,15,15));
        add(new Zone(2,20,18));
        add(new Zone(3,53,50));
        add(new Zone(4,60,52));
    }};
}
