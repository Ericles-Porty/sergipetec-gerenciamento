package xyz.xpto.gerenciamento.infra.repositories.statusTarefa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import xyz.xpto.gerenciamento.application.interfaces.repositories.StatusTarefaRepository;
import xyz.xpto.gerenciamento.domain.entities.StatusTarefa;

@Repository
public class StatusTarefaRepositoryImpl implements StatusTarefaRepository {

    private final StatusTarefaJpaRepository statusTarefaJpaRepository;

    public StatusTarefaRepositoryImpl(StatusTarefaJpaRepository statusTarefaJpaRepository) {
        this.statusTarefaJpaRepository = statusTarefaJpaRepository;
    }

    @Override
    public StatusTarefa salvar(StatusTarefa statusTarefa) {
        return statusTarefaJpaRepository.save(statusTarefa);
    }

    @Override
    public Optional<StatusTarefa> buscarPorId(Long id) {
        return statusTarefaJpaRepository.buscarPorId(id);
    }

    @Override
    public List<StatusTarefa> buscarTodos() {
        return statusTarefaJpaRepository.buscarTodos();
    }

    @Override
    public void modificarNome(Long id, String nome) {
        statusTarefaJpaRepository.modificarNome(id, nome);
    }

    @Override
    public void deletar(Long id) {
        statusTarefaJpaRepository.deletar(id);
    }

}
