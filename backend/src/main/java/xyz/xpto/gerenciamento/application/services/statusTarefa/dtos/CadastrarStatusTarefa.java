package xyz.xpto.gerenciamento.application.services.statusTarefa.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastrarStatusTarefa() {

    public record Request(
            @NotBlank(message = "Nome é obrigatório") 
            @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres") 
            String nome) {}

    public record Response(long id, String nome) {}
}
