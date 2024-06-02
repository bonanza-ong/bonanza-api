package ong.bonanza.beneficiarioapi.domain.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "enderecos_pessoas")
public class EnderecoPessoa {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @ManyToOne
    private Pessoa pessoa;

    private String cep;

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String cidade;

    private String bairro;

    @ManyToOne
    private Localizacao localizacao;

}
