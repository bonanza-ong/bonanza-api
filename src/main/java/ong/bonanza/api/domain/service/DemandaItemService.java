package ong.bonanza.api.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.Beneficiario;
import ong.bonanza.api.domain.entity.DemandaItem;
import ong.bonanza.api.domain.enumeration.StatusDemandaItem;
import ong.bonanza.api.domain.exception.DemandaItemInvalidoException;
import ong.bonanza.api.domain.exception.DemandaItemNaoEncontradaException;
import ong.bonanza.api.domain.exception.RecursoInvalidoException.Atributo;
import ong.bonanza.api.domain.repository.DemandaItemRepository;

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

        if (demandaItem.getQuantidadeSolicitada() < 1)
            throw new DemandaItemInvalidoException(
                    new Atributo("quantidadeSolicitada", demandaItem.getQuantidadeSolicitada().toString(),
                            "Deve ser maior que zero"));

        demandaItem.setStatus(StatusDemandaItem.AGUARDANDO_CONFIRMACAO);
        demandaItem.setQuantidadeAtendida(0);
        demandaItem.setVersionNum(0L);
        return demandaItemRepository.save(demandaItem);
    }

}
