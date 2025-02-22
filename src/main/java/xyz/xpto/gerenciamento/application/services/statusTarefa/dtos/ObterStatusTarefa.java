package xyz.xpto.gerenciamento.application.services.statusTarefa.dtos;

import lombok.Builder;

public record ObterStatusTarefa() {

    public record Request(Long id) {}

    @Builder
    public record Response(Long id, String nome) {}

}
