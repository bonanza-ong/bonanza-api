package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.beneficiarioapi.domain.entity.Beneficiario;
import ong.bonanza.beneficiarioapi.domain.entity.Padrinho;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;

public interface PadrinhoRepository extends JpaRepository<Padrinho, UUID> {

    Boolean existsByPessoa(Pessoa pessoa);


    Optional<Padrinho> findByPessoaAndApadrinhadosContaining(Pessoa pessoa, Beneficiario beneficiario);

}
