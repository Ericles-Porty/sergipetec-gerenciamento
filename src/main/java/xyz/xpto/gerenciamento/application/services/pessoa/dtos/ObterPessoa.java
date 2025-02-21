package xyz.xpto.gerenciamento.application.services.pessoa.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
public record ObterPessoa() {
    public record Request(long id) {}

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    public record Response(
            long id,
            String nome,
            EquipeResponse equipe) {
        public record EquipeResponse(long id, String nome) {}
    }

}
