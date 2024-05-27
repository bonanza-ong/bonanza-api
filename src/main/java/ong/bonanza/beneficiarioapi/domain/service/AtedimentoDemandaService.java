package ong.bonanza.beneficiarioapi.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Doacao;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusDemandaItem;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusDoacao;
import ong.bonanza.beneficiarioapi.domain.exception.DoacaoExcedeuQuantidadeDemandaItemException;
import ong.bonanza.beneficiarioapi.domain.exception.DoacaoParaDemandaComStatusInvalidoException;
import ong.bonanza.beneficiarioapi.domain.repository.DoacaoRepository;

@RequiredArgsConstructor
@Service
public class AtedimentoDemandaService {

    private final StatusDoacao[] STATUS_DOACAO_CONTABILIZAVEIS = {
            StatusDoacao.INICIADA,
            StatusDoacao.COMPLETA
    };

    private final StatusDemandaItem[] STATUS_DEMANDA_ITEM_INAPTOS = {
            StatusDemandaItem.AGUARDANDO_CONFIRMACAO
    };

    private final StatusDemandaItem[] STATUS_DEMANDA_ITEM_FINALIZADOS = {
            StatusDemandaItem.ATENDIDA,
            StatusDemandaItem.CANCELADA
    };

    private final DoacaoRepository doacaoRepository;

    @Transactional
    public void inciarAtendimento(Doacao doacao) {

        if (List.of(STATUS_DEMANDA_ITEM_INAPTOS).contains(doacao.getDemanda().getStatus()))
            throw new DoacaoParaDemandaComStatusInvalidoException(doacao);

        if (List.of(STATUS_DEMANDA_ITEM_FINALIZADOS).contains(doacao.getDemanda().getStatus()))
            throw new DoacaoParaDemandaComStatusInvalidoException(doacao);

        Integer quantidadeAtendidaAtual = doacaoRepository
                .findByDemandaAndStatusIn(doacao.getDemanda(), STATUS_DOACAO_CONTABILIZAVEIS)
                .stream()
                .map(Doacao::getQuantidade)
                .mapToInt(Integer::intValue)
                .sum();

        if (quantidadeAtendidaAtual + doacao.getQuantidade() > doacao.getDemanda().getQuantidadeSolicitada())
            throw new DoacaoExcedeuQuantidadeDemandaItemException(doacao);

        doacao.setStatus(StatusDoacao.INICIADA);
        doacao.getDemanda().setQuantidadeAtendida(quantidadeAtendidaAtual + doacao.getQuantidade());
        doacao.getDemanda().setStatus(StatusDemandaItem.EM_PROGRESSO);
        doacaoRepository.save(doacao);
    }

}
