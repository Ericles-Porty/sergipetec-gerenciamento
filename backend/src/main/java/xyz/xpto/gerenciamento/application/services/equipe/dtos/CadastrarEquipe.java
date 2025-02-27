package xyz.xpto.gerenciamento.application.services.equipe.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record CadastrarEquipe() {
    public record Request(
        @NotBlank(message = "O nome da equipe é obrigatório.")
        @Size(min = 3, max = 100, message = "O nome da equipe deve conter entre 3 e 100 caracteres.")
        String nome) {}

    @Builder
    public record Response(Long id, String nome) {}
}
