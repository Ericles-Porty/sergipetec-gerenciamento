package xyz.xpto.gerenciamento.application.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import xyz.xpto.gerenciamento.domain.entities.Projeto;

public interface ProjetoRepository {

    Projeto salvar(Projeto projeto);

    Optional<Projeto> buscarPorId(Long id);

    List<Projeto> buscarTodos();

    Projeto atualizar(Long id, Projeto projeto);

    void deletar(Long id);

    void modificarStatusProjeto(Long id, Long idStatusProjeto);

    void associarEquipe(Long id, Long idEquipe);

    void desassociarEquipe(Long id);

}
