package xyz.xpto.gerenciamento.application.services.tarefa.dtos;

import java.util.List;

import lombok.Builder;

public record ObterTarefas() {
	public record Request() {}

	@Builder
	public record Response(List<TarefaResponse> tarefas) {
		@Builder
		public record TarefaResponse(
				Long id,
				String titulo,
				String descricao,
				String status,
				String pessoaResponsavel,
				String projeto) {}
	}
}