package models;

import jms.Event;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String id;
    private Role role;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    @Singular
    private Set<String> zones = new HashSet<>();
}
