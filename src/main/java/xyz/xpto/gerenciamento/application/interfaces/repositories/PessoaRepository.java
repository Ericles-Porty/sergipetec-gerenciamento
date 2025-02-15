package xyz.xpto.gerenciamento.application.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import xyz.xpto.gerenciamento.domain.entities.Pessoa;

public interface PessoaRepository {
    Pessoa salvar(Pessoa pessoa);

    Optional<Pessoa> buscarPorId(Long id);

    List<Pessoa> buscarTodos();

    void deletar(Long id);
}