package ong.bonanza.api.domain.exception;

public class ReferenciaCiclicaException extends RuntimeException {

    protected <T> ReferenciaCiclicaException(T clazz, String cliclo) {
        super(String.format("Recurso (%s) possui seguinte referencia ciclica (%s)", clazz.toString(), cliclo));
    }

}
