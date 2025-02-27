package xyz.xpto.gerenciamento.application.services.tarefa.dtos;

public record DesassociarTarefaPessoa() {
	public record Request(Long idTarefa, Long idPessoa) {}

	public record Response() {}
}
