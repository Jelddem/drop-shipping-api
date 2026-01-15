package com.drop.shiping.api.drop_shiping_api.auth.services.Impl;

import com.drop.shiping.api.drop_shiping_api.auth.services.AuthService;
import com.drop.shiping.api.drop_shiping_api.users.entities.User;
import com.drop.shiping.api.drop_shiping_api.users.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.drop.shiping.api.drop_shiping_api.security.JwtConfig.SECRET_KEY;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserRepository repository;

    public AuthServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Optional<User> getUser(String token) {
        String identifier;
        if (token == null || token.isEmpty()) return Optional.empty();

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(SECRET_KEY).build()
                    .parseSignedClaims(token).getPayload();
            identifier = claims.getSubject();
        } catch(JwtException e) {
            LOGGER.warn("Invalid JWT token: {}", e.getMessage());
            return Optional.empty();
        }

        return isNumeric(identifier)
                ? repository.findByPhoneNumber(Long.parseLong(identifier))
                : repository.findByEmail(identifier);
    }

    public boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
