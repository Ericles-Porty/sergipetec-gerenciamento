package xyz.xpto.gerenciamento.application.services.statusProjeto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.xpto.gerenciamento.application.interfaces.repositories.StatusProjetoRepository;
import xyz.xpto.gerenciamento.application.mappers.StatusProjetoMapper;

@Service
@RequiredArgsConstructor
public class StatusProjetoService {

    private final StatusProjetoRepository repository;
    private final StatusProjetoMapper mapper;
}
