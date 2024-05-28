package ong.bonanza.beneficiarioapi.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ong.bonanza.beneficiarioapi.domain.entity.Beneficiario;
import ong.bonanza.beneficiarioapi.domain.exception.BeneficiarioNaoEncontradoException;
import ong.bonanza.beneficiarioapi.domain.repository.BeneficiarioRepository;

import lombok.RequiredArgsConstructor;

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
