package xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObterPessoasResponse {
    private List<PessoaResponse> pessoas;

    public record PessoaResponse(long id, String nome, EquipeResponse equipe) {}

    public record EquipeResponse(Long id, String nome) {}
}
