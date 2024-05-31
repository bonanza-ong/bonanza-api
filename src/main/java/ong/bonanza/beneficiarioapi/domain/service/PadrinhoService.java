package ong.bonanza.beneficiarioapi.domain.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Beneficiario;
import ong.bonanza.beneficiarioapi.domain.entity.Padrinho;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusPadrinho;
import ong.bonanza.beneficiarioapi.domain.exception.PadrinhoJaExistenteException;
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

    public List<Padrinho> buscarPorStatus(StatusPadrinho status, int page, int size) {
        return padrinhoRepository.findAllByStatus(
                status,
                PageRequest.of(
                        page,
                        size,
                        Sort.by("updatedAt").descending()))
                .toList();
    }

}
