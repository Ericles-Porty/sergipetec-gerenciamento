package xyz.xpto.gerenciamento.application.services.pessoa.dtos;

import java.util.List;

import lombok.Builder;

public record ObterPessoas() {
    @Builder
    public record Response(List<PessoaResponse> pessoas) {

        @Builder
        public record PessoaResponse(long id, String nome, EquipeResponse equipe) {}

        @Builder
        public record EquipeResponse(Long id, String nome) {}
    }

    public record Request() {}
}