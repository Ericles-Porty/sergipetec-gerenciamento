package xyz.xpto.gerenciamento.api.extensions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardResponse<R> {
    private boolean success;
    private int code;
    private R data;
    private String message;
    private List<String> errors;

    /**
     * Success Responses
     */
    public static <R> ResponseEntity<StandardResponse<R>> success(R data) {
        return ResponseEntity.ok(
                new StandardResponse<>(true, 200, data, "Operação realizada com sucesso.", null));
    }

    public static <R> ResponseEntity<StandardResponse<R>> success(R data, int code) {
        return ResponseEntity.status(code).body(
                new StandardResponse<>(true, code, data, "Operação realizada com sucesso.", null));
    }

    public static <R> ResponseEntity<StandardResponse<R>> created(R data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardResponse<>(true, 201, data, "Recurso criado com sucesso.", null));
    }

    public static <R> ResponseEntity<StandardResponse<R>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new StandardResponse<>(true, 204, null, "Nenhum conteúdo disponível.", null));
    }

    /**
     * Information Responses
     */
    public static <R> ResponseEntity<StandardResponse<R>> notModified() {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                .body(new StandardResponse<>(true, 304, null, "Nenhuma modificação realizada.", null));
    }

    /**
     * Client Error Responses
     */
    public static <R> ResponseEntity<StandardResponse<R>> badRequest(List<String> errors) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StandardResponse<>(false, 400, null, "Requisição inválida.", errors));
    }

    public static <R> ResponseEntity<StandardResponse<R>> badRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StandardResponse<>(false, 400, null, message, Collections.singletonList(message)));
    }

    public static <R> ResponseEntity<StandardResponse<R>> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new StandardResponse<>(false, 401, null, "Não autorizado.", null));
    }

    public static <R> ResponseEntity<StandardResponse<R>> forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new StandardResponse<>(false, 403, null, "Acesso proibido.", null));
    }

    public static <R> ResponseEntity<StandardResponse<R>> notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new StandardResponse<>(false, 404, null, "Recurso não encontrado.", null));
    }

    /**
     * Server Error Responses
     */
    public static <R> ResponseEntity<StandardResponse<R>> error(List<String> errors) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StandardResponse<>(false, 500, null, "Erro interno no servidor.", errors));
    }

    public static <R> ResponseEntity<StandardResponse<R>> error(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StandardResponse<>(false, 500, null, message, Collections.singletonList(message)));
    }
}
