package xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoa;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObterPessoaResponse {
    private long id;
    private String nome;
    private EquipeResponse equipe;

    public record EquipeResponse(Long id, String nome) {}
}
