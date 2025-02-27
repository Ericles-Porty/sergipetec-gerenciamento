package xyz.xpto.gerenciamento.application.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import xyz.xpto.gerenciamento.domain.entities.Pessoa;

public interface PessoaRepository {
    
    Pessoa salvar(Pessoa pessoa);

    Optional<Pessoa> buscarPorId(Long id);

    List<Pessoa> buscarTodos();

    void modificarNome(Long id, String nome);

    void deletar(Long id);

    void associarEquipe(Long id, Long idEquipe);

    void desassociarEquipe(Long id);

}