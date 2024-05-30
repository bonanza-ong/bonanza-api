package ong.bonanza.beneficiarioapi.domain.exception;

import ong.bonanza.beneficiarioapi.domain.entity.AtendimentoDemandaItem;

public class AtendimentoDemandaItemParaDemandaComStatusInvalidoException extends RuntimeException {

    public AtendimentoDemandaItemParaDemandaComStatusInvalidoException(AtendimentoDemandaItem atendimentoDemandaItem) {
        super(String.format(
                "DemandaItem (id=%s) não pode ser atendida pois seu status é (statusDemandaItem=%s)",
                atendimentoDemandaItem.getDemanda().getId().toString(),
                atendimentoDemandaItem.getDemanda().getStatus().toString()));
    }

}
