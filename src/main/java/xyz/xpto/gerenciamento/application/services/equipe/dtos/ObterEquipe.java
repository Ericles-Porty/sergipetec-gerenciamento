package xyz.xpto.gerenciamento.application.services.equipe.dtos;

import java.util.List;

import jakarta.validation.constraints.Min;
import lombok.Builder;

@Builder
public record ObterEquipe() {
    public record Request(
            @Min(value = 1, message = "O id da equipe é obrigatório e deve ser maior que zero.") Long id) {}

    @Builder
    public record Response(Long id, String nome, List<Pessoa> pessoas, List<Projeto> projetos) {
        @Builder
        public record Pessoa(Long id, String nome) {}

        @Builder
        public record Projeto(Long id, String nome) {}
    }
}
