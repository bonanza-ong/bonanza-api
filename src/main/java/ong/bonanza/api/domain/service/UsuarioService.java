package ong.bonanza.api.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.client.AuthClient;
import ong.bonanza.api.domain.entity.Usuario;
import ong.bonanza.api.domain.exception.UsuarioJaExistenteException;
import ong.bonanza.api.domain.exception.UsuarioNaoEncontradoException;
import ong.bonanza.api.domain.repository.UsuarioRepository;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final AuthClient authClient;

    public Usuario atualAutenticado() {
        return authClient
                .obterUsuarioAtualAutenticado()
                .orElseThrow(() -> UsuarioNaoEncontradoException.buscaPorAutenticado());
    }

    public Usuario cadastrar(Usuario usuario) {

        if (usuarioRepository.existsById(usuario.getId()))
            throw UsuarioJaExistenteException.comId(usuario.getId());

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(() -> UsuarioNaoEncontradoException.buscaPorId(id));
    }

}
