package xyz.xpto.gerenciamento.application.services.tarefa.dtos;

public record AssociarTarefaPessoa() {
	public record Request(Long idTarefa, Long idPessoa) {}

	public record Response() {}
}
