package xyz.xpto.gerenciamento.application.services.projeto.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;

public record ObterProjetos() {

    public record Request() {}

    @Builder
    public record Response(List<ProjetoResponse> projetos) {

        @Builder
        public record ProjetoResponse(
                Long id,
                String nome,
                String descricao,
                LocalDate dataInicio,
                LocalDate dataFim,
                String status) {}
    }

}
