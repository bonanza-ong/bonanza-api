package ong.bonanza.beneficiarioapi.adapter.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.adapter.client.keycloak.KeycloakClient;
import ong.bonanza.beneficiarioapi.adapter.client.keycloak.dto.UserDTO;
import ong.bonanza.beneficiarioapi.domain.entity.Usuario;
import ong.bonanza.beneficiarioapi.domain.repository.UsuarioRepository;

@RequiredArgsConstructor
@Repository
public class UsuarioRepositoryKeycloak implements UsuarioRepository {

    private final UsuarioRepositoryKeycloakMapper mapper;

    private final KeycloakClient keycloakClient;

    @Override
    public Optional<Usuario> findById(UUID id) {
        return keycloakClient
                .buscarUsuarioPorId(id)
                .map(mapper::toUsuario);
    }

    @Override
    public List<Usuario> findByEmail(String email, Integer max) {
        return mapper.toUsuarioList(keycloakClient.buscarUsuariosPorEmail(email, max));
    }

    @Mapper
    public interface UsuarioRepositoryKeycloakMapper {

        List<Usuario> toUsuarioList(List<UserDTO> userDTOList);

        @Mapping(target = "ativo", source = "enabled")
        @Mapping(target = "emailVerificado", source = "emailVerified")
        Usuario toUsuario(UserDTO userDTO);

    }

}
