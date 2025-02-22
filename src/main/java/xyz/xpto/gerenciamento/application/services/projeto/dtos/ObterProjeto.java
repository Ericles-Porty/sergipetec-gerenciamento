package xyz.xpto.gerenciamento.application.services.projeto.dtos;

import java.time.LocalDate;

import lombok.Builder;

public record ObterProjeto() {

    public record Request(Long id) {}

    @Builder
    public record Response(
            Long id,
            String nome,
            String descricao,
            LocalDate dataInicio,
            LocalDate dataFim,
            String equipeResponsavel,
            String status) {}

}
