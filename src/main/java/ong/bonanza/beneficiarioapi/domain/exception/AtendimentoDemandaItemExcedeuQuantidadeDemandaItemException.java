package ong.bonanza.beneficiarioapi.domain.exception;

import ong.bonanza.beneficiarioapi.domain.entity.AtendimentoDemandaItem;

public class AtendimentoDemandaItemExcedeuQuantidadeDemandaItemException extends RuntimeException {

    public AtendimentoDemandaItemExcedeuQuantidadeDemandaItemException(AtendimentoDemandaItem atendimentoDemandaItem) {
        super(String.format(
                "DemandaItem (id=%s) teve (quantidadeAtendida=%s) + (quantidadeAtendimento=%s) > (quantidadeSolicitada=%s)",
                atendimentoDemandaItem.getDemanda().getId().toString(),
                atendimentoDemandaItem.getDemanda().getQuantidadeSolicitada().toString(),
                atendimentoDemandaItem.getQuantidade()));
    }

}
