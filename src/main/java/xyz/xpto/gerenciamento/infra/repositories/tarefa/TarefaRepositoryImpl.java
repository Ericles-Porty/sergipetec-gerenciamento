package xyz.xpto.gerenciamento.infra.repositories.tarefa;

import java.util.List;
import java.util.Optional;

import xyz.xpto.gerenciamento.application.interfaces.repositories.TarefaRepository;
import xyz.xpto.gerenciamento.domain.entities.Tarefa;

public class TarefaRepositoryImpl implements TarefaRepository {

    private final TarefaJpaRepository tarefaJpaRepository;

    public TarefaRepositoryImpl(TarefaJpaRepository tarefaJpaRepository) {
        this.tarefaJpaRepository = tarefaJpaRepository;
    }

    @Override
    public Tarefa salvar(Tarefa tarefa) {
        return tarefaJpaRepository.save(tarefa);
    }

    @Override
    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaJpaRepository.buscarPorId(id);
    }

    @Override
    public List<Tarefa> buscarTodos() {
        return tarefaJpaRepository.buscarTodos();
    }

    @Override
    public Tarefa atualizar(Long id, Tarefa tarefa) {
        Tarefa tarefaAtual = tarefaJpaRepository.buscarPorId(id).
                orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        tarefaAtual.setTitulo(tarefa.getTitulo());
        tarefaAtual.setDescricao(tarefa.getDescricao());

        return tarefaJpaRepository.save(tarefaAtual);
    }

    @Override
    public void deletar(Long id) {
        tarefaJpaRepository.deletar(id);
    }

    @Override
    public void modificarStatusTarefa(Long id, Long idStatusTarefa) {
        tarefaJpaRepository.modificarStatusTarefa(id, idStatusTarefa);
    }

    @Override
    public void associarPessoa(Long id, Long idPessoa) {
        tarefaJpaRepository.associarPessoa(id, idPessoa);
    }

    @Override
    public void desassociarPessoa(Long id) {
        tarefaJpaRepository.desassociarPessoa(id);
    }

}
