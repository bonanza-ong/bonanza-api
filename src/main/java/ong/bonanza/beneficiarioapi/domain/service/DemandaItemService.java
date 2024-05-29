package ong.bonanza.beneficiarioapi.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Beneficiario;
import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusDemandaItem;
import ong.bonanza.beneficiarioapi.domain.exception.DemandaItemNaoEncontradaException;
import ong.bonanza.beneficiarioapi.domain.repository.DemandaItemRepository;

@RequiredArgsConstructor
@Service
public class DemandaItemService {

    private final DemandaItemRepository demandaItemRepository;

    public DemandaItem buscarPorIdEBeneficiario(UUID demandaItemId, Beneficiario beneficiario) {
        return demandaItemRepository
                .findByIdAndBeneficiario(demandaItemId, beneficiario)
                .orElseThrow(
                        () -> DemandaItemNaoEncontradaException.buscaPorIdEBeneficiario(demandaItemId, beneficiario));
    }

    public DemandaItem cadastrar(DemandaItem demandaItem) {
        demandaItem.setStatus(StatusDemandaItem.AGUARDANDO_CONFIRMACAO);
        return demandaItemRepository.save(demandaItem);
    }

}
