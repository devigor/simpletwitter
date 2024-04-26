package study.security.simpletwitter.services.user;

import study.security.simpletwitter.dto.RegisterRequestDTO;
import study.security.simpletwitter.entities.Role;
import study.security.simpletwitter.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    User findUserById(Long id);
    void createUser(RegisterRequestDTO registerRequestDTO, Role basicRole);
    List<User> findAllUsers();
}
