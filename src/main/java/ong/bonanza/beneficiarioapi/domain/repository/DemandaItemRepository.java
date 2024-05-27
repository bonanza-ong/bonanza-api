package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.beneficiarioapi.domain.entity.Beneficiario;
import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;

public interface DemandaItemRepository extends JpaRepository<DemandaItem, UUID> {

    Optional<DemandaItem> findByIdAndBeneficiario(UUID id, Beneficiario beneficiario);

}
