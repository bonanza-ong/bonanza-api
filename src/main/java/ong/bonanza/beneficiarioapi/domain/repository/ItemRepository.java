package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ong.bonanza.beneficiarioapi.domain.entity.CategoriaItem;
import ong.bonanza.beneficiarioapi.domain.entity.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query(value = "WITH RECURSIVE Categoria_Hierarquia AS (" +
            "    SELECT id" +
            "    FROM Categorias_itens" +
            "    WHERE id = :categoriaId" +
            "    UNION ALL" +
            "    SELECT c.id" +
            "    FROM Categorias_itens c" +
            "    INNER JOIN Categoria_Hierarquia ch ON c.pai_id = ch.id" +
            ")" +
            "SELECT i.*" +
            "FROM Item i" +
            "INNER JOIN Categoria_Hierarquia ch ON i.categoria_id = ch.id", nativeQuery = true)
    List<Item> findItemsByCategoriaId(@Param("categoriaId") UUID categoriaId);

    List<Item> findByCategoriasIdIn(UUID categoriaItemId);

}
