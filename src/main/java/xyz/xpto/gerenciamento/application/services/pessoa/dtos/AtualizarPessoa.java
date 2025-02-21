package xyz.xpto.gerenciamento.application.services.pessoa.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarPessoa() {
    public record Request(
            @Min(value = 1, message = "Id no corpo da requisição é obrigatório e deve ser maior que zero") long id,
            @NotBlank(message = "Nome é obrigatório") @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres") String nome) {}

    public record Response(long id, String nome) {}
}
