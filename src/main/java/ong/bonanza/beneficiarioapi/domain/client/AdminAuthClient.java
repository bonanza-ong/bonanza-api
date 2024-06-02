package ong.bonanza.beneficiarioapi.domain.client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ong.bonanza.beneficiarioapi.domain.entity.Usuario;

/**
 * Interface para operações administrativas de controle de usuários no sistema.
 * Apenas administradores podem realizar as operações definidas nesta interface.
 * <p>
 * 
 * @author Jhonata Peres
 * @since 1.0
 */

public interface AdminAuthClient {

    Optional<Usuario> buscarUsuarioPorId(UUID id);

    List<Usuario> buscarUsuariosPorEmail(String email, Integer max);

}
