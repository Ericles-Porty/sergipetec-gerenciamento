package xyz.xpto.gerenciamento.application.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import xyz.xpto.gerenciamento.domain.entities.StatusProjeto;

public interface StatusProjetoRepository {

    StatusProjeto salvar(StatusProjeto statusProjeto);

    Optional<StatusProjeto> buscarPorId(Long id);

    List<StatusProjeto> buscarTodos();

    void modificarNome(Long id, String nome);

    void deletar(Long id);

}
