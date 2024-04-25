package study.security.simpletwitter.infra.security;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.time.Instant;

public interface Jwt {
    JwtClaimsSet createJwtClaims(String issuer, String subject, Instant issuedAt, Instant expiresAt);
    String encodeJwt(JwtClaimsSet claims);
}
