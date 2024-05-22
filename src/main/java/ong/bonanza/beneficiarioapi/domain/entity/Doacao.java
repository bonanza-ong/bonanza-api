package ong.bonanza.beneficiarioapi.domain.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "doacoes")
public class Doacao {

    @Id
    private UUID id;

    @ManyToOne
    private Provedor provedor;

    @ManyToOne
    private DemandaItem demanda;

    private Integer quantidadeAtendida;

}
