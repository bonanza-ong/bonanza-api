package ong.bonanza.beneficiarioapi.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.repository.PessoaRepository;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa buscarPorId(UUID id) {

        // TODO PessoaNaoEncontradaException

        return pessoaRepository
                .findById(id)
                .orElseThrow(null);
    }

}
