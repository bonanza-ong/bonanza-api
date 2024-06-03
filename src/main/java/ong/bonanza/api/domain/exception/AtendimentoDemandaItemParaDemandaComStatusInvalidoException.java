package ong.bonanza.api.domain.exception;

import ong.bonanza.api.domain.entity.AtendimentoDemandaItem;

public class AtendimentoDemandaItemParaDemandaComStatusInvalidoException extends RuntimeException {

    public AtendimentoDemandaItemParaDemandaComStatusInvalidoException(AtendimentoDemandaItem atendimentoDemandaItem) {
        super(String.format(
                "DemandaItem (id=%s) não pode ser atendida pois seu status é (statusDemandaItem=%s)",
                atendimentoDemandaItem.getDemanda().getId().toString(),
                atendimentoDemandaItem.getDemanda().getStatus().toString()));
    }

}
