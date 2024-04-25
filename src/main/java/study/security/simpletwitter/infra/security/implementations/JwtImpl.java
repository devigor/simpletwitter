package study.security.simpletwitter.infra.security.implementations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import study.security.simpletwitter.infra.security.Jwt;

import java.time.Instant;

@Configuration
public class JwtImpl implements Jwt {
    private final JwtEncoder jwtEncoder;

    public JwtImpl(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public JwtClaimsSet createJwtClaims(String issuer, String subject, Instant issuedAt, Instant expiresAt) {
        return JwtClaimsSet
                .builder()
                .issuer(issuer)
                .subject(subject)
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .build();
    }

    public String encodeJwt(JwtClaimsSet claims) {
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
