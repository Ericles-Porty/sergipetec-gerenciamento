package xyz.xpto.gerenciamento.application.services.statusTarefa.dtos;

import java.util.List;

import lombok.Builder;

public record ObterStatusTarefas() {

    public record Request() {}

    @Builder
    public record Response(List<StatusTarefaResponse> statusTarefas) {

        @Builder
        public record StatusTarefaResponse(long id, String nome) {}
    }

}
