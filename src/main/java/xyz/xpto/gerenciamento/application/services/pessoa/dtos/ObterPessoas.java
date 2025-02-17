package xyz.xpto.gerenciamento.application.services.pessoa.dtos;

import java.util.List;

public record ObterPessoas() {

    public record Response(List<PessoaResponse> pessoas) {

        public record PessoaResponse(long id, String nome, EquipeResponse equipe) {}

        public record EquipeResponse(Long id, String nome) {}
    }

    public record Request() {}
}