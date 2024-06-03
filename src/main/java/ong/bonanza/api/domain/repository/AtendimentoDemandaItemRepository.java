package ong.bonanza.api.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.api.domain.entity.AtendimentoDemandaItem;
import ong.bonanza.api.domain.entity.DemandaItem;
import ong.bonanza.api.domain.enumeration.StatusAtendimentoDemandaItem;

public interface AtendimentoDemandaItemRepository extends JpaRepository<AtendimentoDemandaItem, UUID> {

    List<AtendimentoDemandaItem> findByDemandaAndStatusIn(DemandaItem demandaItem, StatusAtendimentoDemandaItem... status);

}
