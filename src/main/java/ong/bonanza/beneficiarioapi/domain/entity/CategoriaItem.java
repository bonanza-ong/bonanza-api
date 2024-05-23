package ong.bonanza.beneficiarioapi.domain.entity;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    // @OneToMany
    // @JoinTable(name = "categorias_sub_categorias", joinColumns = @JoinColumn(name = "categoria_pai_id"), inverseJoinColumns = @JoinColumn(name = "sub_categoria_id"))
    // private List<CategoriaItem> subCategorias;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
