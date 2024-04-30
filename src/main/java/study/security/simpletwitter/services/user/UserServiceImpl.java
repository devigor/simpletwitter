package study.security.simpletwitter.services.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import study.security.simpletwitter.entities.User.DTOs.RegisterRequestDTO;
import study.security.simpletwitter.entities.Role.Role;
import study.security.simpletwitter.entities.User.User;
import study.security.simpletwitter.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void createUser(RegisterRequestDTO registerRequestDTO, Role basicRole) {
        User newUser = new User();
        newUser.setUsername(registerRequestDTO.username());
        newUser.setPassword(bCryptPasswordEncoder.encode(registerRequestDTO.password()));
        newUser.setRoles(Set.of(basicRole));

        userRepository.save(newUser);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
