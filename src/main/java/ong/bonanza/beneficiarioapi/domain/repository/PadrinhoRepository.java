package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.beneficiarioapi.domain.entity.Padrinho;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusPadrinho;

public interface PadrinhoRepository extends JpaRepository<Padrinho, UUID> {

    Boolean existsByPessoa(Pessoa pessoa);

    Page<Padrinho> findAllByStatus(StatusPadrinho status, Pageable pageable);

}
