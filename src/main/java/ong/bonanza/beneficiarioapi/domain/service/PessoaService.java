package ong.bonanza.beneficiarioapi.domain.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.entity.Usuario;
import ong.bonanza.beneficiarioapi.domain.exception.PessoaJaExistenteException;
import ong.bonanza.beneficiarioapi.domain.exception.PessoaNaoEncontradaException;
import ong.bonanza.beneficiarioapi.domain.repository.PessoaRepository;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa cadastrar(Pessoa pessoa) {

        if (pessoaRepository.existsByUsuario(pessoa.getUsuario()))
            throw PessoaJaExistenteException.comUsuario(pessoa.getUsuario());

        return pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPorUsuario(Usuario usuario) {
        return pessoaRepository
                .findByUsuario(usuario)
                .orElseThrow(() -> PessoaNaoEncontradaException.buscaPorUsuario(usuario));
    }

}
