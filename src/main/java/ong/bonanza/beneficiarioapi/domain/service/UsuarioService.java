package ong.bonanza.beneficiarioapi.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.client.AuthClient;
import ong.bonanza.beneficiarioapi.domain.entity.Usuario;
import ong.bonanza.beneficiarioapi.domain.exception.UsuarioNaoEncontradoException;
import ong.bonanza.beneficiarioapi.domain.repository.UsuarioRepository;

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
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(() -> UsuarioNaoEncontradoException.buscaPorId(id));
    }

}
