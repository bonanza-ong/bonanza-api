package ong.bonanza.beneficiarioapi.domain.client;

import java.util.Optional;

import ong.bonanza.beneficiarioapi.domain.entity.Usuario;

public interface AuthClient {

    Optional<Usuario> obterUsuarioAtualAutenticado();

}
