package xyz.xpto.gerenciamento.api.configurations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import xyz.xpto.gerenciamento.api.exceptions.CustomValidationException;
import xyz.xpto.gerenciamento.api.extensions.StandardResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções de validação customizadas.
     */
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<StandardResponse<Void>> handleCustomValidationException(CustomValidationException ex) {
        return StandardResponse.badRequest(ex.getErrors());
    }

    /**
     * Trata exceções de validação do Bean Valid.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardResponse<Void>> handleValidationException(ValidationException ex) {
        return StandardResponse.badRequest(List.of(ex.getMessage()));
    }

    /**
     * Trata exceções de validação de constraints.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardResponse<Void>> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());
        return StandardResponse.badRequest(errors);
    }

    /**
     * Trata exceções de validação de argumentos de métodos.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Trata exceções de status de resposta.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardResponse<Void>> handleResponseStatusException(
            ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(new StandardResponse<>(false, ex.getStatusCode().value(), null,
                        "Houve um erro ao processar a requisição.",
                        List.of(ex.getReason())));
    }

    /**
     * Trata exceções de leitura de JSON inválido.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardResponse<Void>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        return StandardResponse.badRequest(List.of("Verifique se o corpo da requisição tem um JSON válido."));
    }

    /**
     * Trata exceções de tipo de mídia não suportado.
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<StandardResponse<Void>> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException ex) {
        return StandardResponse
                .badRequest(List.of("Verifique se o cabeçalho Content-Type da requisição está correto."));
    }

    /**
     * Trata exceções de acesso inválido ao banco de dados.
     */
    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<StandardResponse<Void>> handleInvalidDataAccessResourceUsageException(
            InvalidDataAccessResourceUsageException ex) {
                System.out.println(ex);
        return StandardResponse.error("Houve um erro ao acessar o banco de dados.");
    }

    // TODO: Adicionar tratamento de exceções gerais ao final do desenvolvimento.
    /**
     * Trata exceções genéricas.
     */
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<StandardResponse<Void>> handleException(Exception ex) {
    // return StandardResponse.error("Houve um erro inesperado ao processar a
    // requisição.");
    // }
}
