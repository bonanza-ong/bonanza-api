package ong.bonanza.beneficiarioapi.application.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Você não está autenticado");
    }

}
