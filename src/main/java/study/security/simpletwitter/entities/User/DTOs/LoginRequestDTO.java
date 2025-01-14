package study.security.simpletwitter.entities.User.DTOs;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Username não pode estar vazio!")
        String username,

        @NotBlank(message = "A senha não pode estar vazia!")
        String password
) {
}
