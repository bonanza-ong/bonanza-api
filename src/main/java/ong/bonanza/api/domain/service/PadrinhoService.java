package ong.bonanza.api.domain.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.Padrinho;
import ong.bonanza.api.domain.enumeration.StatusPadrinho;
import ong.bonanza.api.domain.exception.PadrinhoJaExistenteException;
import ong.bonanza.api.domain.repository.PadrinhoRepository;

@RequiredArgsConstructor
@Service
public class PadrinhoService {

    private final PadrinhoRepository padrinhoRepository;

    public Padrinho cadastrar(Padrinho padrinho) {

        if (padrinhoRepository.existsByPessoa(padrinho.getPessoa()))
            throw PadrinhoJaExistenteException.comPessoa(padrinho.getPessoa());

        return padrinhoRepository.save(padrinho);
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
