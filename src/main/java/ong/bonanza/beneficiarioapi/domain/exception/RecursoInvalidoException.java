package ong.bonanza.beneficiarioapi.domain.exception;

import java.util.List;

public abstract class RecursoInvalidoException extends RuntimeException {

    protected <T> RecursoInvalidoException(T clazz, Atributo... atributos) {
        super(String.format("Recurso (%s) inválido, atributos -> (%s)",
                clazz.toString(),
                String.join(";", List.of(atributos).stream().map(Atributo::toString).toList())));
    }

    public record Atributo(String nome, String valor, String mensagem) {

        public Atributo(String nome, String valor) {
            this(nome, valor, "inválido");
        }

        public String toString() {
            return String.format("[%s=%s], mensagem=[%s]", nome, valor, mensagem);
        }

    }
}
