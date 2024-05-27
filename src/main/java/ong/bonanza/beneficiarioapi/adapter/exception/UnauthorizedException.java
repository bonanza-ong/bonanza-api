package ong.bonanza.beneficiarioapi.adapter.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Você não está autenticado");
    }

}
