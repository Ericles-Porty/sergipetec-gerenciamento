package xyz.xpto.gerenciamento.application.services.tarefa.dtos;

import lombok.Builder;

public record AtualizarTarefa() {
	public record Request(
			Long id,
			String titulo,
			String descricao,
			Long idStatusTarefa,
			Long idPessoaResponsavel) {}

	@Builder
	public record Response(
			Long id,
			String titulo,
			String descricao,
			Long idStatusTarefa,
			Long idPessoaResponsavel,
			Long idProjeto) {}
}
