package ong.bonanza.beneficiarioapi.adapter.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.adapter.client.keycloak.KeycloakClient;
import ong.bonanza.beneficiarioapi.adapter.client.keycloak.dto.UserDTO;
import ong.bonanza.beneficiarioapi.adapter.client.keycloak.dto.UserInfoDTO;
import ong.bonanza.beneficiarioapi.domain.client.AdminAuthClient;
import ong.bonanza.beneficiarioapi.domain.client.AuthClient;
import ong.bonanza.beneficiarioapi.domain.entity.Usuario;

@RequiredArgsConstructor
@Repository
public class KeycloakAuthService implements AdminAuthClient, AuthClient {

    private final UsuarioRepositoryKeycloakMapper mapper;

    private final KeycloakClient keycloakClient;

    @Override
    public Optional<Usuario> obterUsuarioAtualAutenticado() {
        return keycloakClient
                .userInfo()
                .map(mapper::toUsuario);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(UUID id) {
        return keycloakClient
                .buscarUsuarioPorId(id)
                .map(mapper::toUsuario);
    }

    @Override
    public List<Usuario> buscarUsuariosPorEmail(String email, Integer max) {
        return mapper.toUsuarioList(keycloakClient.buscarUsuariosPorEmail(email, max));
    }

    @Mapper
    public interface UsuarioRepositoryKeycloakMapper {

        List<Usuario> toUsuarioList(List<UserDTO> userDTOList);

        @Mapping(target = "ativo", source = "enabled")
        @Mapping(target = "emailVerificado", source = "emailVerified")
        Usuario toUsuario(UserDTO userDTO);

        @Mapping(target = "id", source = "sub")
        @Mapping(target = "ativo", ignore = true)
        @Mapping(target = "emailVerificado", source = "email_verified")
        Usuario toUsuario(UserInfoDTO userInfoDTO);

    }

}
