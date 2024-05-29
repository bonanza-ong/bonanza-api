package ong.bonanza.beneficiarioapi.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusAtendimentoDemandaItem;

@Getter
@Setter
@ToString
@Entity
@Table(name = "atendimentos_demandas_itens")
public class AtendimentoDemandaItem {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Provedor provedor;

    @Enumerated(EnumType.STRING)
    private StatusAtendimentoDemandaItem status;

    @ManyToOne
    private DemandaItem demanda;

    @NotNull
    private Integer quantidade;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
