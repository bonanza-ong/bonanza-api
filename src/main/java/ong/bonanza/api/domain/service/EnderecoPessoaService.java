package ong.bonanza.api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.EnderecoPessoa;
import ong.bonanza.api.domain.entity.Pessoa;
import ong.bonanza.api.domain.repository.EnderecoPessoaRepository;

@RequiredArgsConstructor
@Service
public class EnderecoPessoaService {

    private final EnderecoPessoaRepository enderecoPessoaRepository;

    public List<EnderecoPessoa> buscarPorPessoa(Pessoa pessoa) {
        return enderecoPessoaRepository.findAllByPessoa(pessoa);
    }

}
