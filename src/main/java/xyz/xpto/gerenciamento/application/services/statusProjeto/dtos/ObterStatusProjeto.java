package xyz.xpto.gerenciamento.application.services.statusProjeto.dtos;

import lombok.Builder;

public record ObterStatusProjeto() {

    public record Request(long id) {}

    @Builder
    public record Response(long id, String nome) {}

}
