package xyz.xpto.gerenciamento.application.services.statusProjeto.dtos;

import lombok.Builder;

public record ObterStatusProjeto() {

    public record Request(Long id) {}

    @Builder
    public record Response(Long id, String nome) {}

}
