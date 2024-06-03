package ong.bonanza.api.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.api.domain.entity.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
