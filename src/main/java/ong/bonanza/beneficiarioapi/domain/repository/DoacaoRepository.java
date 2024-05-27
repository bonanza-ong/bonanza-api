package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;
import ong.bonanza.beneficiarioapi.domain.entity.Doacao;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusDoacao;

public interface DoacaoRepository extends JpaRepository<Doacao, UUID> {

    List<Doacao> findByDemandaAndStatusIn(DemandaItem demandaItem, StatusDoacao... status);

}
