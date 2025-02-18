package xyz.xpto.gerenciamento.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.CadastrarPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.ObterPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.ObterPessoas;
import xyz.xpto.gerenciamento.api.extensions.StandardResponse;
import xyz.xpto.gerenciamento.api.extensions.ValidationExtension;
import xyz.xpto.gerenciamento.application.services.PessoaService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getPessoas() {
        ObterPessoas.Response response = pessoaService.obterPessoas(new ObterPessoas.Request());
        return StandardResponse.success(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPessoa(@PathVariable long id) {
        ObterPessoa.Response response = pessoaService.obterPessoa(new ObterPessoa.Request(id));
        return StandardResponse.success(response);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postPessoa(@Valid @RequestBody CadastrarPessoa.Request request,
            BindingResult bindingResult) {
        ValidationExtension.validateBindingResult(bindingResult);

        var response = pessoaService.cadastrarPessoa(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePessoa(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }

}
