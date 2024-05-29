package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.beneficiarioapi.domain.entity.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByNomeContaining(String nome, Pageable pageable);
}
