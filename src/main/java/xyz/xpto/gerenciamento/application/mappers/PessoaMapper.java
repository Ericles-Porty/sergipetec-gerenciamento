package xyz.xpto.gerenciamento.application.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import xyz.xpto.gerenciamento.application.services.pessoa.dtos.CadastrarPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.ObterPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.ObterPessoas;
import xyz.xpto.gerenciamento.domain.entities.Equipe;
import xyz.xpto.gerenciamento.domain.entities.Pessoa;

@Component
public class PessoaMapper {

    public ObterPessoas.Response pessoaToObterPessoasResponse(List<Pessoa> pessoas) {
        return ObterPessoas.Response.builder()
                .pessoas(pessoas
                        .stream()
                        .map(p -> new ObterPessoas.Response.PessoaResponse(p.getId(), p.getNome(),
                                equipeToObterPessoasResponseEquipeResponse(p.getEquipe())))
                        .toList())
                .build();
    }

    private ObterPessoas.Response.EquipeResponse equipeToObterPessoasResponseEquipeResponse(Equipe equipe) {
        if (equipe == null)
            return null;

        return ObterPessoas.Response.EquipeResponse.builder()
                .id(equipe.getId())
                .nome(equipe.getNome())
                .build();
    }

    public ObterPessoa.Response pessoaToObterPessoaResponse(Pessoa pessoa) {
        return ObterPessoa.Response.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .equipe(equipeToObterPessoaResponseEquipeResponse(pessoa.getEquipe()))
                .build();
    }

    private ObterPessoa.Response.EquipeResponse equipeToObterPessoaResponseEquipeResponse(Equipe equipe) {
        if (equipe == null)
            return null;

        return new ObterPessoa.Response.EquipeResponse(equipe.getId(), equipe.getNome());
    }

    public CadastrarPessoa.Response pessoaToCadastrarPessoaResponse(Pessoa pessoa) {
        return new CadastrarPessoa.Response(pessoa.getId(), pessoa.getNome());
    }
}