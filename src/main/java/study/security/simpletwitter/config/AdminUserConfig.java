package study.security.simpletwitter.config;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import study.security.simpletwitter.entities.Role;
import study.security.simpletwitter.entities.User;
import study.security.simpletwitter.repositories.RolesRepository;
import study.security.simpletwitter.repositories.UserRepository;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class AdminUserConfig implements CommandLineRunner {
    private final RolesRepository rolesRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = rolesRepository.findByName(Role.ROLES_VALUES.ADMIN.name());

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin ja existe");
                },
                () -> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        );
    }
}
