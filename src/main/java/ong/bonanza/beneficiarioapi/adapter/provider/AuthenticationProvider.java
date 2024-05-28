package ong.bonanza.beneficiarioapi.adapter.provider;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ong.bonanza.beneficiarioapi.adapter.exception.UnauthorizedException;

@Component
public class AuthenticationProvider {

    public UUID getAuthenticatedUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            throw new UnauthorizedException();

        return UUID.fromString(authentication.getName());
    }

}
