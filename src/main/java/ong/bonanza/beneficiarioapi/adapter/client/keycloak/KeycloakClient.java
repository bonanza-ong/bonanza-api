package ong.bonanza.beneficiarioapi.adapter.client.keycloak;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.adapter.client.keycloak.dto.UserDTO;
import ong.bonanza.beneficiarioapi.adapter.provider.AuthenticationProvider;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class KeycloakClient {

    private final WebClient webClient;

    private final String realm;

    private final AuthenticationProvider authenticationProvider;

    public Optional<UserDTO> buscarUsuarioPorId(UUID id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("admin/realms/{realm}/users/{id}")
                        .build(realm, id))
                .header("Authorization", String.format("Bearer %s", authenticationProvider.token()))
                .retrieve()
                .bodyToMono(UserDTO.class)
                .onErrorResume(WebClientResponseException.NotFound.class, notFound -> Mono.empty())
                .blockOptional();
    }

    public List<UserDTO> buscarUsuariosPorEmail(String email, Integer max) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("admin/realms/{realm}/users")
                        .queryParam("email", email)
                        .queryParam("max", max)
                        .build(realm))
                .header("Authorization", String.format("Bearer %s", authenticationProvider.token()))
                .retrieve()
                .bodyToFlux(UserDTO.class)
                .collectList()
                .block();
    }

}
