package xyz.xpto.gerenciamento.application.services.equipe.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record AtualizarEquipe() {
    public record Request(
    @Min(value = 1, message = "O id da equipe é obrigatório e deve ser maior que zero.")    
    Long id, 
    @NotBlank(message = "O nome da equipe é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome da equipe deve conter entre 3 e 100 caracteres.")
    String nome) {}

    @Builder
    public record Response(Long id, String nome) {}
}
