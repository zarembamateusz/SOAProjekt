package Models;

import lombok.Value;

import java.util.Set;

@Value
public class User {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String login;
    private final String password;
    private final Role role;
    private final Set<Zone> zones;

}
