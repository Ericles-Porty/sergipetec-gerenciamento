package xyz.xpto.gerenciamento.application.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import xyz.xpto.gerenciamento.domain.entities.Tarefa;

public interface TarefaRepository {

    Tarefa salvar(Tarefa tarefa);

    Optional<Tarefa> buscarPorId(Long id);

    List<Tarefa> buscarTodos();

    Tarefa atualizar(Long id, Tarefa tarefa);

    void deletar(Long id);

    void modificarStatusTarefa(Long id, Long idStatusTarefa);

    void associarPessoa(Long id, Long idPessoa);

    void desassociarPessoa(Long id);

}
