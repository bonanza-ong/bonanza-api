package ong.bonanza.beneficiarioapi.domain.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.entity.Provedor;
import ong.bonanza.beneficiarioapi.domain.exception.ProvedorNaoEncontradoException;
import ong.bonanza.beneficiarioapi.domain.repository.ProvedorRepository;

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
