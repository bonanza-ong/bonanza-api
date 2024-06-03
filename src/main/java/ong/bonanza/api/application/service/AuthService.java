package ong.bonanza.api.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    boolean possuiAlgumaRole(String... roles);

    UUID idUsuarioAutenticado();

}
