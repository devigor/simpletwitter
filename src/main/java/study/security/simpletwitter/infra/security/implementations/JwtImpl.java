package study.security.simpletwitter.infra.security.implementations;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import study.security.simpletwitter.infra.security.Jwt;

import java.time.Instant;

@Configuration
@AllArgsConstructor
public class JwtImpl implements Jwt {
    private final JwtEncoder jwtEncoder;

    public JwtClaimsSet createJwtClaims(String issuer, String subject, Instant issuedAt, Instant expiresAt, String scope) {
        return JwtClaimsSet
                .builder()
                .issuer(issuer)
                .subject(subject)
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .claim("scope", scope)
                .build();
    }

    public String encodeJwt(JwtClaimsSet claims) {
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
