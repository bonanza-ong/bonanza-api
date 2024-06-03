package ong.bonanza.api.application.exception;

public class ForbiddenException extends RuntimeException {

    // public ForbiddenException() {
    // super("Sem permissão");
    // }

    public ForbiddenException(String para) {
        super(String.format("Sem permissão para [%s]", para));
    }

}
