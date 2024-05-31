package ong.bonanza.beneficiarioapi.adapter.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("Sem permissão");
    }

}
