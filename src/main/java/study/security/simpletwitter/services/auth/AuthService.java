package study.security.simpletwitter.services.auth;

import study.security.simpletwitter.entities.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> findByUsername(String username);
}