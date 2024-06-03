package ong.bonanza.api.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.api.domain.entity.Pessoa;
import ong.bonanza.api.domain.entity.Provedor;

public interface ProvedorRepository extends JpaRepository<Provedor, UUID> {

    Optional<Provedor> findByPessoa(Pessoa pessoa);

}
