package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.beneficiarioapi.domain.entity.EnderecoPessoa;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;

public interface EnderecoPessoaRepository extends JpaRepository<EnderecoPessoa, UUID> {

    List<EnderecoPessoa> findAllByPessoa(Pessoa pessoa);

}
