package ong.bonanza.api.domain.exception;

import java.util.UUID;

import ong.bonanza.api.domain.entity.Beneficiario;

public class BeneficiarioNaoEncontradoException extends RecursoNaoEncontradoException {

    private BeneficiarioNaoEncontradoException(String consulta) {
        super(Beneficiario.class, consulta);
    }

    public static BeneficiarioNaoEncontradoException buscaPorId(UUID id) {
        return new BeneficiarioNaoEncontradoException(String.format("[id=%s]", id.toString()));
    }

}
