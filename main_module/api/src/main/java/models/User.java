package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
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
    @Builder.Default
    private Set<String> zones = new HashSet<>();
}
