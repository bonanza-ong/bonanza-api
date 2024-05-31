package ong.bonanza.beneficiarioapi.domain.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Usuario {

    private UUID id;

    private String email;

    private String emailVerificado;

    private Boolean ativo;

}
