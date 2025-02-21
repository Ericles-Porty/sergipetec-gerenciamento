package xyz.xpto.gerenciamento.application.services.statusProjeto.dtos;

import java.util.List;

import lombok.Builder;

public record ObterStatusProjetos() {

    public record Request() {}

    @Builder
    public record Response(List<StatusProjetoResponse> statusProjetoResponses) {

        @Builder
        public record StatusProjetoResponse(long id, String nome) {}
    }

}
