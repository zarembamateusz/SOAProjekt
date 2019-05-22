package models;

import entity.Role;
import lombok.*;

import javax.enterprise.inject.Model;
import java.util.HashSet;
import java.util.Set;

@Model
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private Role role;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    @Builder.Default
    private Set<String> zones = new HashSet<>();
}
