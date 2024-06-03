package ong.bonanza.api.domain.client;

import java.util.Optional;

import ong.bonanza.api.domain.entity.Usuario;

public interface AuthClient {

    Optional<Usuario> obterUsuarioAtualAutenticado();

}
