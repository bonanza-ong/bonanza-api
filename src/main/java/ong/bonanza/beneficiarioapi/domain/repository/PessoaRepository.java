package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.entity.Usuario;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    Optional<Pessoa> findByUsuario(Usuario usuario);

}
