package xyz.xpto.gerenciamento.application.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import xyz.xpto.gerenciamento.domain.entities.Equipe;

public interface EquipeRepository {
    
    Equipe salvar(Equipe equipe);

    Optional<Equipe> buscarPorId(Long id);

    List<Equipe> buscarTodos();

    void modificarNome(Long id, String nome);

    void deletar(Long id);

    void adicionarPessoa(Long id, Long idPessoa);

    void removerPessoa(Long idPessoa);

}