package xyz.xpto.gerenciamento.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import xyz.xpto.gerenciamento.application.services.equipe.dtos.AtualizarEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.CadastrarEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.ObterEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.ObterEquipes;
import xyz.xpto.gerenciamento.domain.entities.Equipe;

@Component
public class EquipeMapper {
    public ObterEquipe.Response equipeToObterEquipeResponse(Equipe equipe) {
        return ObterEquipe.Response.builder()
                .id(equipe.getId())
                .nome(equipe.getNome())
                .pessoas(equipe.getPessoas().stream().map(pessoa -> ObterEquipe.Response.Pessoa.builder()
                        .id(pessoa.getId())
                        .nome(pessoa.getNome())
                        .build()).collect(Collectors.toList()))
                .projetos(equipe.getProjetos().stream().map(projeto -> ObterEquipe.Response.Projeto.builder()
                        .id(projeto.getId())
                        .nome(projeto.getNome())
                        .build()).collect(Collectors.toList()))
                .build();
    }

    public ObterEquipes.Response equipesToObterEquipesResponse(List<Equipe> equipes) {
        return ObterEquipes.Response.builder()
                .equipes(equipes.stream()
                        .map(equipe -> ObterEquipes.Response.Equipe.builder()
                                .id(equipe.getId())
                                .nome(equipe.getNome())
                                .pessoas(equipe.getPessoas().stream()
                                        .map(pessoa -> ObterEquipes.Response.Equipe.Pessoa.builder()
                                                .id(pessoa.getId())
                                                .nome(pessoa.getNome())
                                                .build())
                                        .collect(Collectors.toList()))
                                .projetos(equipe.getProjetos().stream()
                                        .map(projeto -> ObterEquipes.Response.Equipe.Projeto.builder()
                                                .id(projeto.getId())
                                                .nome(projeto.getNome())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();

    }

    public CadastrarEquipe.Response equipeToCadastrarEquipeResponse(Equipe equipe) {
        return CadastrarEquipe.Response.builder()
                .id(equipe.getId())
                .nome(equipe.getNome())
                .build();
    }

    public AtualizarEquipe.Response equipeToAtualizarEquipeResponse(Equipe equipe) {
        return AtualizarEquipe.Response.builder()
                .id(equipe.getId())
                .nome(equipe.getNome())
                .build();
    }
}
