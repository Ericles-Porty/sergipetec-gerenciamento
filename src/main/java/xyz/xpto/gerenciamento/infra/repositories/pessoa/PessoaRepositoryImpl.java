package xyz.xpto.gerenciamento.infra.repositories.pessoa;

import xyz.xpto.gerenciamento.application.interfaces.repositories.PessoaRepository;
import xyz.xpto.gerenciamento.domain.entities.Pessoa;

import java.util.List;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PessoaRepositoryImpl implements PessoaRepository {

    private final PessoaJpaRepository pessoaJpaRepository;

    public PessoaRepositoryImpl(PessoaJpaRepository pessoaJpaRepository) {
        this.pessoaJpaRepository = pessoaJpaRepository;
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaJpaRepository.save(pessoa);
    }

    @Override
    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaJpaRepository.findById(id);
    }

    @Override
    public List<Pessoa> buscarTodos() {
        return pessoaJpaRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        pessoaJpaRepository.deleteById(id);
    }
}