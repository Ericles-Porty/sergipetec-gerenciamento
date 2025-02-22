package xyz.xpto.gerenciamento.application.services.projeto.dtos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record CadastrarProjeto() {

        public record Request(
                        @NotBlank(message = "Nome é obrigatório") @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres") String nome,
                        @NotBlank(message = "Descrição é obrigatória") @Length(max = 500, message = "Descrição deve ter no máximo 500 caracteres") String descricao,
                        @PastOrPresent(message = "Data de início deve ser no passado ou presente") LocalDate dataInicio,
                        LocalDate dataFim

        ) {}

        @Builder
        public record Response(
                        Long id,
                        String nome,
                        String descricao,
                        LocalDate dataInicio,
                        LocalDate dataFim,
                        String status) {}
}
