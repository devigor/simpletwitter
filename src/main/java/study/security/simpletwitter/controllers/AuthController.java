package study.security.simpletwitter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.security.simpletwitter.dto.LoginRequestDTO;
import study.security.simpletwitter.dto.LoginResponseDTO;
import study.security.simpletwitter.infra.security.Jwt;
import study.security.simpletwitter.services.auth.AuthService;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final Jwt jwtInfra;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(AuthService authService, BCryptPasswordEncoder bCryptPasswordEncoder, Jwt jwtInfra) {
        this.authService = authService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtInfra = jwtInfra;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String username = loginRequestDTO.username();
        var user = authService.findByUsername(username);

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequestDTO, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Username or password invalid");
        }

        long expiresTime = 300L;
        Instant nowDate = Instant.now();
        Instant expiresAt = nowDate.plusSeconds(expiresTime);

        JwtClaimsSet claims = jwtInfra.createJwtClaims(
                "minitwitter-spring",
                user.get().getId().toString(),
                nowDate,
                expiresAt
        );
        String jwtValue = jwtInfra.encodeJwt(claims);

        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresTime));
    }
}
