package ong.bonanza.beneficiarioapi.domain.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private UUID id;

    @Transient
    private String email;

    @Transient
    private String emailVerificado;

    @Transient
    private Boolean ativo;

}
