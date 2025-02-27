package xyz.xpto.gerenciamento.application.services.tarefa.dtos;

import lombok.Builder;

public record ObterTarefa() {
	public record Request(Long id) {}

	@Builder
	public record Response(
			Long id,
			String titulo,
			String descricao,
			String status,
			String pessoaResponsavel,
			String projeto) {}

}
