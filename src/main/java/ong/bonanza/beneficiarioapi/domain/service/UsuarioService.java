package ong.bonanza.beneficiarioapi.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Usuario;
import ong.bonanza.beneficiarioapi.domain.exception.UsuarioNaoEncontradoException;
import ong.bonanza.beneficiarioapi.domain.repository.UsuarioRepository;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(() -> UsuarioNaoEncontradoException.buscaPorId(id));
    }

    public List<Usuario> buscarPorEmail(String email, Integer max) {
        return usuarioRepository.findByEmail(email, max);
    }

}
