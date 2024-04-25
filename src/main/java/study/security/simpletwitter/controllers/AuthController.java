package study.security.simpletwitter.controllers;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
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
import study.security.simpletwitter.dto.RegisterRequestDTO;
import study.security.simpletwitter.entities.Role;
import study.security.simpletwitter.infra.security.Jwt;
import study.security.simpletwitter.services.roles.RoleService;
import study.security.simpletwitter.services.user.UserService;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final RoleService roleService;
    private final Jwt jwtInfra;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder, Jwt jwtInfra) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtInfra = jwtInfra;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String username = loginRequestDTO.username();
        var user = userService.findByUsername(username);

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

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        var basicRole = roleService.findRoleByName(Role.ROLES_VALUES.BASIC.name());
        var userFromDb = userService.findByUsername(registerRequestDTO.username());

        if (userFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        userService.createUser(registerRequestDTO, basicRole);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
