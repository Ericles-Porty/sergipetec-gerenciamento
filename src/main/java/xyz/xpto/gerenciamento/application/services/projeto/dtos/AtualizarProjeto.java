package xyz.xpto.gerenciamento.application.services.projeto.dtos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

public record AtualizarProjeto() {

        public record Request(
                        @Min(value = 1, message = "Id no corpo da requisição é obrigatório e deve ser maior que zero") Long id,
                        @Length(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres") String nome,
                        @Length(max = 500, message = "Descrição deve ter no máximo 500 caracteres") String descricao,
                        @PastOrPresent(message = "Data de início deve ser no passado ou presente") LocalDate dataInicio,
                        LocalDate dataFim) {}

        @Builder
        public record Response(
                        Long id,
                        String nome,
                        String descricao,
                        LocalDate dataInicio,
                        LocalDate dataFim,
                        String status) {}

}
