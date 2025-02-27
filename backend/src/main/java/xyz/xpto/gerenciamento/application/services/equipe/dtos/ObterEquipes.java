package xyz.xpto.gerenciamento.application.services.equipe.dtos;

import java.util.List;

import lombok.Builder;

public record ObterEquipes() {
    public record Request() {}

    @Builder
    public record Response(List<Equipe> equipes) {

        @Builder
        public record Equipe(Long id, String nome, List<Pessoa> pessoas, List<Projeto> projetos) {
            @Builder
            public record Pessoa(Long id, String nome) {}

            @Builder
            public record Projeto(Long id, String nome) {}
        }

    }

}
