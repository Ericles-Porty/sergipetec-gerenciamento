package xyz.xpto.gerenciamento.application.services.tarefa.dtos;

public record ModificarStatusTarefa() {
	public record Request(Long idTarefa, Long idStatusTarefa) {}

	public record Response() {}
}
