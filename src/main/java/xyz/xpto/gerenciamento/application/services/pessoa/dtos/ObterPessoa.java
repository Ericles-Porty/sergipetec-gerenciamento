package xyz.xpto.gerenciamento.application.services.pessoa.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import xyz.xpto.gerenciamento.domain.entities.Equipe;
import xyz.xpto.gerenciamento.domain.entities.Pessoa;

public record ObterPessoa() {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Response(
            long id,
            String nome,
            EquipeResponse equipe) {

        public ObterPessoa.Response toResponse(Pessoa pessoa) {
            return new Response(pessoa.getId(), pessoa.getNome(), pessoa.getEquipe() != null
                    ? new EquipeResponse(pessoa.getEquipe().getId(), pessoa.getEquipe().getNome())
                    : null);
        }

        public record EquipeResponse(Long id, String nome) {
            public EquipeResponse toEquipe(Equipe equipe) {
                return new EquipeResponse(equipe.getId(), equipe.getNome());
            }
        }

    }

    public record Request(long id) {}

}