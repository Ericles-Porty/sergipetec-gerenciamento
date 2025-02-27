package xyz.xpto.gerenciamento.application.services.tarefa.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record CadastrarTarefa() {

	public record Request(
			@NotNull(message = "O título é obrigatório") @Length(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres") String titulo,
			@NotNull(message = "A descrição é obrigatória") @Length(min = 3, max = 1000, message = "A descrição deve ter entre 3 e 1000 caracteres") String descricao,
			@NotNull(message = "O id do projeto é obrigatório") Long idProjeto,
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
