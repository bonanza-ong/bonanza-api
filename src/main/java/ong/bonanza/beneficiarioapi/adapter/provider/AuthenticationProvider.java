package ong.bonanza.beneficiarioapi.adapter.provider;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import ong.bonanza.beneficiarioapi.application.exception.UnauthorizedException;

@Component
public class AuthenticationProvider {

    public UUID authenticatedUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            throw new UnauthorizedException();

        return UUID.fromString(authentication.getName());
    }

    public String token() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            throw new UnauthorizedException();

        if (!(authentication.getCredentials() instanceof Jwt))
            throw new UnauthorizedException();

        Jwt jwt = (Jwt) authentication.getCredentials();

        return jwt.getTokenValue();

    }

    public boolean hasRole(String role) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            throw new UnauthorizedException();

        final String _role = String.format("ROLE_%s", role);

        return authentication
                .getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().toUpperCase().equals(_role));
    }

}
