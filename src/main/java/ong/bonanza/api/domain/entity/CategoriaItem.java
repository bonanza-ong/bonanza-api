package ong.bonanza.api.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ong.bonanza.api.domain.exception.CategoriaItemComReferenciaCliclicaException;

@Getter
@Setter
@ToString
@Entity
@Table(name = "categorias_itens")
public class CategoriaItem {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String nome;

    @ManyToOne
    private CategoriaItem pai;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void setPai(CategoriaItem pai) {
        if (possuiCiclo(pai))
            throw new CategoriaItemComReferenciaCliclicaException(pai, this);

        this.pai = pai;
    }

    private boolean possuiCiclo(CategoriaItem pai) {

        Set<UUID> idsVisitados = new HashSet<>();

        do {
            if (pai == null)
                return false;

            idsVisitados.add(pai.id);
            if (idsVisitados.contains(this.id))
                return true;
        } while ((pai = pai.pai == null ? null : pai.pai) != null);

        return false;
    }

}
