package xyz.xpto.gerenciamento.application.services.pessoa.dtos;

import lombok.Builder;

@Builder
public record ObterPessoa() {
	public record Request(long id) {}

	@Builder
	public record Response(
			long id,
			String nome,
			EquipeResponse equipe) {
		public record EquipeResponse(long id, String nome) {}
	}

}
