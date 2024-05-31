package ong.bonanza.beneficiarioapi.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ong.bonanza.beneficiarioapi.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

}
