package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

}
