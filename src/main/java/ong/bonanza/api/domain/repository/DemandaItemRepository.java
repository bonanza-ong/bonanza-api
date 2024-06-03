package ong.bonanza.api.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.api.domain.entity.Beneficiario;
import ong.bonanza.api.domain.entity.DemandaItem;

public interface DemandaItemRepository extends JpaRepository<DemandaItem, UUID> {

    Optional<DemandaItem> findByIdAndBeneficiario(UUID id, Beneficiario beneficiario);

}
