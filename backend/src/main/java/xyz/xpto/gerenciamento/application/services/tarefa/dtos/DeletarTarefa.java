package xyz.xpto.gerenciamento.application.services.tarefa.dtos;

public record DeletarTarefa() {
	public record Request(Long id) {}

	public record Response() {}
}
