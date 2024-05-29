package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;
import ong.bonanza.beneficiarioapi.domain.entity.AtendimentoDemandaItem;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusAtendimentoDemandaItem;

public interface AtendimentoDemandaItemRepository extends JpaRepository<AtendimentoDemandaItem, UUID> {

    List<AtendimentoDemandaItem> findByDemandaAndStatusIn(DemandaItem demandaItem, StatusAtendimentoDemandaItem... status);

}
