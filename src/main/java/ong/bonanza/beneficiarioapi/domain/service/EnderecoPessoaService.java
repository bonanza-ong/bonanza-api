package ong.bonanza.beneficiarioapi.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.EnderecoPessoa;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.repository.EnderecoPessoaRepository;

@RequiredArgsConstructor
@Service
public class EnderecoPessoaService {

    private final EnderecoPessoaRepository enderecoPessoaRepository;

    public List<EnderecoPessoa> buscarPorPessoa(Pessoa pessoa) {
        return enderecoPessoaRepository.findAllByPessoa(pessoa);
    }

}
