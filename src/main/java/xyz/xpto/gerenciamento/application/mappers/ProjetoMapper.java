package xyz.xpto.gerenciamento.application.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import xyz.xpto.gerenciamento.application.services.projeto.dtos.AtualizarProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.CadastrarProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.ObterProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.ObterProjetos;
import xyz.xpto.gerenciamento.domain.entities.Projeto;

@Component
public class ProjetoMapper {
	public ObterProjeto.Response projetoToObterProjetoResponse(Projeto projeto) {
		return ObterProjeto.Response.builder()
				.id(projeto.getId())
				.nome(projeto.getNome())
				.descricao(projeto.getDescricao())
				.dataInicio(projeto.getDataInicio())
				.dataFim(projeto.getDataFim())
				.equipeResponsavel(
						Optional.ofNullable(projeto.getEquipe()).map(equipe -> equipe.getNome()).orElse(null))
				.status(projeto.getStatusProjeto().getNome())
				.build();
	}

	public ObterProjetos.Response projetosToObterProjetosResponse(List<Projeto> projetos) {
		return ObterProjetos.Response.builder()
				.projetos(projetos.stream()
						.map(projeto -> ObterProjetos.Response.ProjetoResponse.builder()
								.id(projeto.getId())
								.nome(projeto.getNome())
								.descricao(projeto.getDescricao())
								.dataInicio(projeto.getDataInicio())
								.dataFim(projeto.getDataFim())
								.equipeResponsavel(Optional.ofNullable(projeto.getEquipe())
										.map(equipe -> equipe.getNome()).orElse(null))
								.status(projeto.getStatusProjeto().getNome())
								.build())
						.collect(Collectors.toList()))
				.build();
	}

	public AtualizarProjeto.Response projetoToAtualizarProjetoResponse(Projeto projeto) {
		return AtualizarProjeto.Response.builder()
				.id(projeto.getId())
				.nome(projeto.getNome())
				.descricao(projeto.getDescricao())
				.dataInicio(projeto.getDataInicio())
				.dataFim(projeto.getDataFim())
				.status(projeto.getStatusProjeto().getNome())
				.build();
	}

	public CadastrarProjeto.Response projetoToCadastrarProjetoResponse(Projeto projeto) {
		return CadastrarProjeto.Response.builder()
				.id(projeto.getId())
				.nome(projeto.getNome())
				.descricao(projeto.getDescricao())
				.dataInicio(projeto.getDataInicio())
				.dataFim(projeto.getDataFim())
				.status(projeto.getStatusProjeto().getNome())
				.build();
	}
}
