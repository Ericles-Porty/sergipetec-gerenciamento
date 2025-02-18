package xyz.xpto.gerenciamento.infra.repositories.equipe;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

import xyz.xpto.gerenciamento.application.interfaces.repositories.EquipeRepository;
import xyz.xpto.gerenciamento.domain.entities.Equipe;

@Component
public class EquipeRepositoryImpl implements EquipeRepository {

    private final EquipeJpaRepository equipeJpaRepository;

    public EquipeRepositoryImpl(EquipeJpaRepository equipeJpaRepository) {
        this.equipeJpaRepository = equipeJpaRepository;
    }

    @Override
    public Equipe salvar(Equipe equipe) {
        return equipeJpaRepository.save(equipe);
    }

    @Override
    public Optional<Equipe> buscarPorId(Long id) {
        return equipeJpaRepository.findById(id);
    }

    @Override
    public List<Equipe> buscarTodos() {
        return equipeJpaRepository.findAll();
    }

    @Override
    public void modificarNome(Long id, String nome) {
        equipeJpaRepository.modificarNome(id, nome);
    }

    @Override
    public void deletar(Long id) {
        equipeJpaRepository.deleteById(id);
    }

    @Override
    public void adicionarPessoa(Long id, Long idPessoa) {
        equipeJpaRepository.adicionarPessoa(id, idPessoa);
    }

    @Override
    public void removerPessoa(Long idPessoa) {
        equipeJpaRepository.removerPessoa(idPessoa);
    }

}
