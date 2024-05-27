package ong.bonanza.beneficiarioapi.domain.exception;

import ong.bonanza.beneficiarioapi.domain.entity.Doacao;

public class DoacaoExcedeuQuantidadeDemandaItemException extends RuntimeException {

    public DoacaoExcedeuQuantidadeDemandaItemException(Doacao doacao) {
        super(String.format(
                "DemandaItem (id=%s) teve (quantidadeAtendida=%s) + (quantidadeAtendimento=%s) > (quantidadeSolicitada=%s)",
                doacao.getDemanda().getId().toString(),
                doacao.getDemanda().getQuantidadeSolicitada().toString(),
                doacao.getQuantidade()));
    }

}
