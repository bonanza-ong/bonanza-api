package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ong.bonanza.beneficiarioapi.domain.entity.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {

}
