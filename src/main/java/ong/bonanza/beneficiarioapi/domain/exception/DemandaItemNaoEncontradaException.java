package ong.bonanza.beneficiarioapi.domain.exception;

import java.util.UUID;

import ong.bonanza.beneficiarioapi.domain.entity.Beneficiario;
import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;

public class DemandaItemNaoEncontradaException extends RecursoNaoEncontradoException {

    private DemandaItemNaoEncontradaException(String consulta) {
        super(DemandaItem.class, consulta);
    }

    public static DemandaItemNaoEncontradaException buscaPorIdEBeneficiario(UUID id, Beneficiario beneficiario) {
        return new DemandaItemNaoEncontradaException(
                String.format("[demandaItemId=%s] e [beneficiarioId=%s]",
                        id.toString(),
                        beneficiario.getId()));
    }

}
