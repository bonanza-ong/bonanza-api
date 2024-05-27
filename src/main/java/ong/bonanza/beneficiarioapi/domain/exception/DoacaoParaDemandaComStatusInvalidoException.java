package ong.bonanza.beneficiarioapi.domain.exception;

import ong.bonanza.beneficiarioapi.domain.entity.Doacao;

public class DoacaoParaDemandaComStatusInvalidoException extends RuntimeException {

    public DoacaoParaDemandaComStatusInvalidoException(Doacao doacao) {
        super(String.format(
                "DemandaItem (id=%s) não pode ser atendida pois seu status é (statusDemandaItem=%s)",
                doacao.getDemanda().getId().toString(),
                doacao.getDemanda().getStatus().toString()));
    }

}
