package ong.bonanza.api.domain.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Provedor> buscarPaginado(String nome, int page, int size) {
        return provedorRepository.findByPessoaNomeContainingIgnoreCase(nome,
                PageRequest.of(
                        page,
                        size,
                        Sort.by("pessoa.nome").ascending()))
                .toList();
    }

}
