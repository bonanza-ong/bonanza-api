package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import ong.bonanza.beneficiarioapi.domain.entity.Usuario;

@Repository
public interface UsuarioRepository {

    Optional<Usuario> findById(UUID id);

    List<Usuario> findByEmail(String email, Integer max);

}
