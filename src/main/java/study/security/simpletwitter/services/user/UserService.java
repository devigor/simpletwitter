package study.security.simpletwitter.services.user;

import study.security.simpletwitter.entities.User.DTOs.RegisterRequestDTO;
import study.security.simpletwitter.entities.Role.Role;
import study.security.simpletwitter.entities.User.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    User findUserById(Long id);
    void createUser(RegisterRequestDTO registerRequestDTO, Role basicRole);
    List<User> findAllUsers();
}
