package ong.bonanza.beneficiarioapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Doacao;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusDoacao;
import ong.bonanza.beneficiarioapi.domain.repository.DoacaoRepository;

@RequiredArgsConstructor
@Service
public class DoacaoService {

    private final StatusDoacao[] STATUS_DOACAO_VALIDAS = { StatusDoacao.ATRIBUIDA, StatusDoacao.COMPLETA };

    private final DoacaoRepository doacaoRepository;

    @Transactional
    public void efetuarDoacao(Doacao doacao) {

        Integer quantidadeAtendidaAtual = doacaoRepository
                .findByDemandaAndStatusIn(doacao.getDemanda(), STATUS_DOACAO_VALIDAS)
                .stream()
                .map(Doacao::getQuantidadeAtendida)
                .mapToInt(Integer::intValue)
                .sum();

        if (quantidadeAtendidaAtual + doacao.getQuantidadeAtendida() > doacao.getDemanda().getQuantidadeSolicitada())
            throw new RuntimeException();

        Doacao doacaoNova = doacaoRepository.save(doacao);

        quantidadeAtendidaAtual = doacaoRepository
                .findByDemandaAndStatusIn(doacao.getDemanda(), STATUS_DOACAO_VALIDAS)
                .stream()
                .filter(d -> !d.getId().toString().equals(doacaoNova.getId().toString()))
                .map(Doacao::getQuantidadeAtendida)
                .mapToInt(Integer::intValue)
                .sum();

        if (quantidadeAtendidaAtual + doacao.getQuantidadeAtendida() > doacao.getDemanda().getQuantidadeSolicitada())
            throw new RuntimeException();

    }

}
