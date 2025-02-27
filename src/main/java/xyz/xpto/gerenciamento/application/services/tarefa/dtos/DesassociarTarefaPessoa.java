package xyz.xpto.gerenciamento.application.services.tarefa.dtos;

public record DesassociarTarefaPessoa() {
	public record Request(Long id) {}

	public record Response() {}
}
