package xyz.xpto.gerenciamento.application.services;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.ObterPessoas;
import xyz.xpto.gerenciamento.domain.entities.Equipe;
import xyz.xpto.gerenciamento.domain.entities.Pessoa;

@Component
@RequiredArgsConstructor
public class PessoaMapper {
    public ObterPessoas.Response.PessoaResponse toResponse(Pessoa pessoa) {
        return ObterPessoas.Response.PessoaResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .equipe(toEquipeResponse(pessoa.getEquipe()))
                .build();
    }

    private ObterPessoas.Response.EquipeResponse toEquipeResponse(Equipe equipe) {
        if (equipe == null)
            return null;

        return ObterPessoas.Response.EquipeResponse.builder()
                .id(equipe.getId())
                .nome(equipe.getNome())
                .build();
    }
}