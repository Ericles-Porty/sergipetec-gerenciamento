package xyz.xpto.gerenciamento.application.services.statusProjeto.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AtualizarStatusProjeto() {

    public record Request(
            @Min(value = 1, message = "Id no corpo da requisição é obrigatório e deve ser maior que zero") long id,
            @NotBlank(message = "Nome é obrigatório") @Length(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres") String nome) {}

    public record Response(long id, String nome) {}
}
