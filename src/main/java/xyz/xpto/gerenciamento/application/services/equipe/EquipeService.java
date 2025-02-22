package xyz.xpto.gerenciamento.application.services.equipe;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import xyz.xpto.gerenciamento.application.interfaces.repositories.EquipeRepository;
import xyz.xpto.gerenciamento.application.mappers.EquipeMapper;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.ApagarEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.AtualizarEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.CadastrarEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.ObterEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.ObterEquipes;
import xyz.xpto.gerenciamento.domain.entities.Equipe;

@Service
@AllArgsConstructor
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final EquipeMapper equipeMapper;

    public CadastrarEquipe.Response cadastrarEquipe(CadastrarEquipe.Request request) {
        var equipe = new Equipe();
        equipe.setNome(request.nome());

        var equipeCriada = equipeRepository.salvar(equipe);
        return equipeMapper.equipeToCadastrarEquipeResponse(equipeCriada);
    }

    public ObterEquipe.Response obterEquipe(ObterEquipe.Request request) {
        var equipe = equipeRepository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe não encontrada"));
        return equipeMapper.equipeToObterEquipeResponse(equipe);
    }

    public ObterEquipes.Response obterEquipes(ObterEquipes.Request request) {
        var equipes = equipeRepository.buscarTodos();
        return equipeMapper.equipesToObterEquipesResponse(equipes);
    }

    public ApagarEquipe.Response apagarEquipe(ApagarEquipe.Request request) {
        equipeRepository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe não encontrada"));

        equipeRepository.deletar(request.id());
        return new ApagarEquipe.Response();
    }

    public AtualizarEquipe.Response atualizarEquipe(Long id, AtualizarEquipe.Request request) {
        var equipe = equipeRepository.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe não encontrada"));

        equipe.setNome(request.nome());
        var equipeAtualizada = equipeRepository.salvar(equipe);
        return equipeMapper.equipeToAtualizarEquipeResponse(equipeAtualizada);
    }
}
