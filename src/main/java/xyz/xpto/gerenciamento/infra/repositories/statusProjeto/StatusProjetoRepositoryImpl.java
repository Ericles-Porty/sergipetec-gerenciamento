package xyz.xpto.gerenciamento.infra.repositories.statusProjeto;

import java.util.List;
import java.util.Optional;

import xyz.xpto.gerenciamento.application.interfaces.repositories.StatusProjetoRepository;
import xyz.xpto.gerenciamento.domain.entities.StatusProjeto;

public class StatusProjetoRepositoryImpl implements StatusProjetoRepository {

    private final StatusProjetoJpaRepository statusProjetoJpaRepository;

    public StatusProjetoRepositoryImpl(StatusProjetoJpaRepository statusProjetoJpaRepository) {
        this.statusProjetoJpaRepository = statusProjetoJpaRepository;
    }

    @Override
    public StatusProjeto salvar(StatusProjeto statusProjeto) {
        return statusProjetoJpaRepository.save(statusProjeto);
    }

    @Override
    public Optional<StatusProjeto> buscarPorId(Long id) {
        return statusProjetoJpaRepository.buscarPorId(id);
    }

    @Override
    public List<StatusProjeto> buscarTodos() {
        return statusProjetoJpaRepository.buscarTodos();
    }

    @Override
    public void modificarNome(Long id, String nome) {
        statusProjetoJpaRepository.modificarNome(id, nome);
    }

    @Override
    public void deletar(Long id) {
        statusProjetoJpaRepository.deletar(id);
    }

}
