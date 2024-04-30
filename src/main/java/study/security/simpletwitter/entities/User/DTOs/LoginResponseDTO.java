package study.security.simpletwitter.entities.User.DTOs;

public record LoginResponseDTO(String accessToken, Long expiresIn) {
}
