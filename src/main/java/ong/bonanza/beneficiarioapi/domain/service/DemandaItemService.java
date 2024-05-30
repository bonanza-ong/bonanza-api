package ong.bonanza.beneficiarioapi.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Beneficiario;
import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusDemandaItem;
import ong.bonanza.beneficiarioapi.domain.exception.DemandaItemInvalidoException;
import ong.bonanza.beneficiarioapi.domain.exception.DemandaItemNaoEncontradaException;
import ong.bonanza.beneficiarioapi.domain.exception.RecursoInvalidoException.Atributo;
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

        if (demandaItem.getQuantidadeSolicitada() < 1)
            throw new DemandaItemInvalidoException(
                    new Atributo("quantidadeSolicitada", demandaItem.getQuantidadeSolicitada().toString(), "Deve ser maior que zero"));

        demandaItem.setStatus(StatusDemandaItem.AGUARDANDO_CONFIRMACAO);
        demandaItem.setQuantidadeAtendida(0);
        return demandaItemRepository.save(demandaItem);
    }

}
