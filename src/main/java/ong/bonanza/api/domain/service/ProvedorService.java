package ong.bonanza.api.domain.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.Pessoa;
import ong.bonanza.api.domain.entity.Provedor;
import ong.bonanza.api.domain.exception.ProvedorNaoEncontradoException;
import ong.bonanza.api.domain.repository.ProvedorRepository;

@RequiredArgsConstructor
@Service
public class ProvedorService {

    private final ProvedorRepository provedorRepository;

    public Provedor buscarPorPessoa(Pessoa pessoa) {
        return provedorRepository
                .findByPessoa(pessoa)
                .orElseThrow(() -> ProvedorNaoEncontradoException.buscaPorPessoa(pessoa));
    }

}
