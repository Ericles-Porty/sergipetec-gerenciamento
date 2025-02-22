package xyz.xpto.gerenciamento.infra.repositories.projeto;

import java.util.List;
import java.util.Optional;

import xyz.xpto.gerenciamento.application.interfaces.repositories.ProjetoRepository;
import xyz.xpto.gerenciamento.domain.entities.Projeto;

public class ProjetoRepositoryImpl implements ProjetoRepository {

    private final ProjetoJpaRepository projetoJpaRepository;

    public ProjetoRepositoryImpl(ProjetoJpaRepository projetoJpaRepository) {
        this.projetoJpaRepository = projetoJpaRepository;
    }

    @Override
    public Projeto salvar(Projeto projeto) {
        return projetoJpaRepository.save(projeto);
    }

    @Override
    public Optional<Projeto> buscarPorId(Long id) {
        return projetoJpaRepository.buscarPorId(id);
    }

    @Override
    public List<Projeto> buscarTodos() {
        return projetoJpaRepository.buscarTodos();
    }

    @Override
    public void deletar(Long id) {
        projetoJpaRepository.deletar(id);
    }

    @Override
    public void modificarStatusProjeto(Long id, Long idStatusProjeto) {
        projetoJpaRepository.modificarStatusProjeto(id, idStatusProjeto);
    }

    @Override
    public void associarEquipe(Long id, Long idEquipe) {
        projetoJpaRepository.associarEquipe(id, idEquipe);
    }

    @Override
    public void desassociarEquipe(Long id) {
        projetoJpaRepository.desassociarEquipe(id);
    }

    @Override
    public Projeto atualizar(Long id, Projeto projeto) {
        Projeto projetoAtual = projetoJpaRepository.findById(id).get();

        projetoAtual.setNome(projeto.getNome());
        projetoAtual.setDescricao(projeto.getDescricao());
        projetoAtual.setDataInicio(projeto.getDataInicio());
        projetoAtual.setDataFim(projeto.getDataFim());

        return projetoJpaRepository.save(projetoAtual);
    }

}
