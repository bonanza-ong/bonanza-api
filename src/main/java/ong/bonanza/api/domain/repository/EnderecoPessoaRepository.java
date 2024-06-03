package ong.bonanza.api.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.api.domain.entity.EnderecoPessoa;
import ong.bonanza.api.domain.entity.Pessoa;

public interface EnderecoPessoaRepository extends JpaRepository<EnderecoPessoa, UUID> {

    List<EnderecoPessoa> findAllByPessoa(Pessoa pessoa);

}
