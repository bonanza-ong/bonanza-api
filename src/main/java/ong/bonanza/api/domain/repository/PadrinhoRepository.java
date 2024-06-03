package ong.bonanza.api.domain.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.api.domain.entity.Padrinho;
import ong.bonanza.api.domain.entity.Pessoa;
import ong.bonanza.api.domain.enumeration.StatusPadrinho;

public interface PadrinhoRepository extends JpaRepository<Padrinho, UUID> {

    Boolean existsByPessoa(Pessoa pessoa);

    Page<Padrinho> findAllByStatus(StatusPadrinho status, Pageable pageable);

}
