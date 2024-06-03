package ong.bonanza.api.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.Beneficiario;
import ong.bonanza.api.domain.exception.BeneficiarioNaoEncontradoException;
import ong.bonanza.api.domain.repository.BeneficiarioRepository;

@RequiredArgsConstructor
@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;

    public Beneficiario buscarPorId(UUID id) {
        return beneficiarioRepository
                .findById(id)
                .orElseThrow(() -> BeneficiarioNaoEncontradoException.buscaPorId(id));
    }

    public List<Beneficiario> buscarTodosPaginado(int page, int size) {
        return beneficiarioRepository.findAll(
                PageRequest.of(
                        page,
                        size,
                        Sort.by("createdAt").descending()))
                .toList();
    }

}
