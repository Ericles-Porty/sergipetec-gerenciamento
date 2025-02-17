package xyz.xpto.gerenciamento.api.exceptions;

import java.util.ArrayList;
import java.util.List;

public class CustomValidationException extends RuntimeException {
    private final List<String> errors;

    public CustomValidationException(String message) {
        super(message);
        this.errors = new ArrayList<>();
        this.errors.add(message);
    }

    public CustomValidationException(List<String> errors) {
        super("Erro de validação.");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}