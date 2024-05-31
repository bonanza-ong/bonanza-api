package ong.bonanza.beneficiarioapi.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusPadrinho;

@Getter
@Setter
@ToString
@Entity
@Table(name = "padrinhos", uniqueConstraints = @UniqueConstraint(columnNames = "pessoa_id"))
public class Padrinho {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPadrinho status;

    @ManyToMany
    @JoinTable(name = "padrinhos_apadrinhados", inverseJoinColumns = @JoinColumn(name = "beneficiario_id"))
    private List<Beneficiario> apadrinhados;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
