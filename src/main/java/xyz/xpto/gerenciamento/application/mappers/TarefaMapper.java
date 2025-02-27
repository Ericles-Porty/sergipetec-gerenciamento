package xyz.xpto.gerenciamento.application.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import xyz.xpto.gerenciamento.application.services.tarefa.dtos.ObterTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.ObterTarefas;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.CadastrarTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.AtualizarTarefa;
import xyz.xpto.gerenciamento.domain.entities.Tarefa;

@Component
public class TarefaMapper {
	public ObterTarefa.Response tarefaToObterTarefaResponse(Tarefa tarefa) {
		return ObterTarefa.Response.builder()
				.id(tarefa.getId())
				.titulo(tarefa.getTitulo())
				.descricao(tarefa.getDescricao())
				.pessoaResponsavel(tarefa.getResponsavel().getNome())
				.status(tarefa.getStatusTarefa().getNome())
				.projeto(tarefa.getProjeto().getNome())
				.build();
	}

	public ObterTarefas.Response tarefasToObterTarefasResponse(List<Tarefa> tarefas) {
		return ObterTarefas.Response.builder()
				.tarefas(tarefas.stream()
						.map(tarefa -> ObterTarefas.Response.TarefaResponse.builder()
								.id(tarefa.getId())
								.titulo(tarefa.getTitulo())
								.descricao(tarefa.getDescricao())
								.pessoaResponsavel(
										Optional.ofNullable(tarefa.getResponsavel()).map(pessoa -> pessoa.getNome())
												.orElse(null))
								.status(tarefa.getStatusTarefa().getNome())
								.projeto(tarefa.getProjeto().getNome())
								.build())
						.collect(Collectors.toList()))
				.build();
	}

	public CadastrarTarefa.Response tarefaToCadastrarTarefaResponse(Tarefa tarefa) {
		return CadastrarTarefa.Response.builder()
				.id(tarefa.getId())
				.titulo(tarefa.getTitulo())
				.descricao(tarefa.getDescricao())
				.idPessoaResponsavel(
						Optional.ofNullable(tarefa.getResponsavel()).map(pessoa -> pessoa.getId()).orElse(null))
				.idStatusTarefa(tarefa.getStatusTarefa().getId())
				.idProjeto(tarefa.getProjeto().getId())
				.build();
	}

	public AtualizarTarefa.Response tarefaToAtualizarTarefaResponse(Tarefa tarefa) {
		return AtualizarTarefa.Response.builder()
				.id(tarefa.getId())
				.titulo(tarefa.getTitulo())
				.descricao(tarefa.getDescricao())
				.idPessoaResponsavel(
						Optional.ofNullable(tarefa.getResponsavel()).map(pessoa -> pessoa.getId()).orElse(null))
				.idStatusTarefa(tarefa.getStatusTarefa().getId())
				.idProjeto(tarefa.getProjeto().getId())
				.build();
	}

}
