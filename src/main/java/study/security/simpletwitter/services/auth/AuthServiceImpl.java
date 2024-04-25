package study.security.simpletwitter.services.auth;

import org.springframework.stereotype.Service;
import study.security.simpletwitter.entities.User;
import study.security.simpletwitter.repositories.UserRepository;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
