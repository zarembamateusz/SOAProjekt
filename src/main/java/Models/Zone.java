package Models;

import lombok.Value;

import java.util.Set;

@Value
public class Zone {
    private final String id;
    private final Set<CarPlace> seats;
    private final Set<User> responsibleUsers;

}
