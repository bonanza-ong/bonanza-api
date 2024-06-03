package ong.bonanza.api.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "itens")
public class Item {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    @ManyToOne
    private CategoriaItem categoriaPrincipal;

    @ManyToMany
    @JoinTable(name = "itens_categorias", inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<CategoriaItem> categorias;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
