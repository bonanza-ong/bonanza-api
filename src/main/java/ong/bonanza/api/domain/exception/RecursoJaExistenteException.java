package ong.bonanza.api.domain.exception;

public class RecursoJaExistenteException extends RuntimeException {

    protected <T> RecursoJaExistenteException(T clazz, String identificador) {
        super(String.format("Recurso (%s) já existente no sistema com o seguinte identificador (%s)",
                clazz.toString(),
                identificador));
    }
}
