package ong.bonanza.api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.AtendimentoDemandaItem;
import ong.bonanza.api.domain.enumeration.StatusAtendimentoDemandaItem;
import ong.bonanza.api.domain.enumeration.StatusDemandaItem;
import ong.bonanza.api.domain.exception.AtendimentoDemandaItemExcedeuQuantidadeDemandaItemException;
import ong.bonanza.api.domain.exception.AtendimentoDemandaItemParaDemandaComStatusInvalidoException;
import ong.bonanza.api.domain.repository.AtendimentoDemandaItemRepository;

@RequiredArgsConstructor
@Service
public class AtendimentoDemandaItemService {

    private final StatusAtendimentoDemandaItem[] STATUS_ATENDIMENTO_DEMANDA_CONTABILIZAVEIS = {
            StatusAtendimentoDemandaItem.INICIADO,
            StatusAtendimentoDemandaItem.COMPLETO
    };

    private final StatusDemandaItem[] STATUS_DEMANDA_ITEM_INAPTOS = {
            StatusDemandaItem.AGUARDANDO_CONFIRMACAO
    };

    private final StatusDemandaItem[] STATUS_DEMANDA_ITEM_FINALIZADOS = {
            StatusDemandaItem.ATENDIDA,
            StatusDemandaItem.CANCELADA
    };

    private final AtendimentoDemandaItemRepository atendimentoDemandaRepository;

    @Transactional
    public AtendimentoDemandaItem inciar(AtendimentoDemandaItem atendimentoDemandaItem) {

        if (List.of(STATUS_DEMANDA_ITEM_INAPTOS).contains(atendimentoDemandaItem.getDemanda().getStatus()))
            throw new AtendimentoDemandaItemParaDemandaComStatusInvalidoException(atendimentoDemandaItem);

        if (List.of(STATUS_DEMANDA_ITEM_FINALIZADOS).contains(atendimentoDemandaItem.getDemanda().getStatus()))
            throw new AtendimentoDemandaItemParaDemandaComStatusInvalidoException(atendimentoDemandaItem);

        Integer quantidadeAtendidaAtual = atendimentoDemandaRepository
                .findByDemandaAndStatusIn(atendimentoDemandaItem.getDemanda(),
                        STATUS_ATENDIMENTO_DEMANDA_CONTABILIZAVEIS)
                .stream()
                .map(AtendimentoDemandaItem::getQuantidade)
                .mapToInt(Integer::intValue)
                .sum();

        if (quantidadeAtendidaAtual + atendimentoDemandaItem.getQuantidade() > atendimentoDemandaItem.getDemanda()
                .getQuantidadeSolicitada())
            throw new AtendimentoDemandaItemExcedeuQuantidadeDemandaItemException(atendimentoDemandaItem);

        atendimentoDemandaItem.setStatus(StatusAtendimentoDemandaItem.INICIADO);
        atendimentoDemandaItem.getDemanda()
                .setQuantidadeAtendida(quantidadeAtendidaAtual + atendimentoDemandaItem.getQuantidade());
        atendimentoDemandaItem.getDemanda().setStatus(StatusDemandaItem.EM_PROGRESSO);

        return atendimentoDemandaRepository.save(atendimentoDemandaItem);
    }

}
