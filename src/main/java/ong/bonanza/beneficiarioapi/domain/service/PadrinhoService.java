package ong.bonanza.beneficiarioapi.domain.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Beneficiario;
import ong.bonanza.beneficiarioapi.domain.entity.Padrinho;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.exception.PadrinhoNaoEncontradoException;
import ong.bonanza.beneficiarioapi.domain.repository.PadrinhoRepository;

@RequiredArgsConstructor
@Service
public class PadrinhoService {

    private final PadrinhoRepository padrinhoRepository;

    public Padrinho cadastrar(Padrinho padrinho) {

        if (padrinhoRepository.existsByPessoa(padrinho.getPessoa()))
            throw PadrinhoJaExistenteException.comPessoa(padrinho.getPessoa());

        return padrinhoRepository.save(padrinho);
    }

    public Padrinho buscarPorPessoaEBeneficiario(Pessoa pessoa, Beneficiario beneficiario) {
        return padrinhoRepository
                .findByPessoaAndApadrinhadosContaining(pessoa, beneficiario)
                .orElseThrow(() -> PadrinhoNaoEncontradoException.buscaPorPessoaEBeneficiario(pessoa, beneficiario));
    }

}
