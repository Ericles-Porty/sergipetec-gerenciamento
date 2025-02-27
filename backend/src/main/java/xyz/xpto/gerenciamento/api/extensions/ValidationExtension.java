package xyz.xpto.gerenciamento.api.extensions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

import xyz.xpto.gerenciamento.api.exceptions.CustomValidationException;

public final class ValidationExtension {
    private ValidationExtension() {
    }

    public static void validateBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult
                    .getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            throw new CustomValidationException(errors);
        }
    }
}
