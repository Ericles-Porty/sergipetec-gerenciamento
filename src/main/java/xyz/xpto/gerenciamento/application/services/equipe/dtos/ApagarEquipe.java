package xyz.xpto.gerenciamento.application.services.equipe.dtos;

import jakarta.validation.constraints.Min;

public record ApagarEquipe() {
    public record Request(
            @Min(value = 1, message = "O id da equipe é obrigatório e deve ser maior que zero.") Long id) {}

    public record Response() {}

}
