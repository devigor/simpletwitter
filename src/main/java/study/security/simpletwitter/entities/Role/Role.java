package study.security.simpletwitter.entities.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;

    @Getter
    public enum ROLES_VALUES {
        ADMIN(1L),
        BASIC(2L);


        final long roleId;

        ROLES_VALUES(long roleId) {
            this.roleId = roleId;
        }
    }
}
