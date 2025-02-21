package xyz.xpto.gerenciamento.application.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import xyz.xpto.gerenciamento.domain.entities.StatusTarefa;

public interface StatusTarefaRepository {

    StatusTarefa salvar(StatusTarefa statusTarefa);

    Optional<StatusTarefa> buscarPorId(Long id);

    List<StatusTarefa> buscarTodos();

    void modificarNome(Long id, String nome);

    void deletar(Long id);

}
