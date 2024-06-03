package ong.bonanza.api.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.api.domain.entity.CategoriaItem;

public interface CategoriaItemRepository extends JpaRepository<CategoriaItem, UUID> {

}
