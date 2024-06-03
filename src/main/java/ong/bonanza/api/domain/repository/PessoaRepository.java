package ong.bonanza.api.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.api.domain.entity.Pessoa;
import ong.bonanza.api.domain.entity.Usuario;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    Boolean existsByUsuario(Usuario usuario);

    Optional<Pessoa> findByUsuario(Usuario usuario);

}
