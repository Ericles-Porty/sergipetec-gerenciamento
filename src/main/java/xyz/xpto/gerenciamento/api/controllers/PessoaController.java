package xyz.xpto.gerenciamento.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.cadastrarPessoa.CadastrarPessoaRequest;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoa.ObterPessoaRequest;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoa.ObterPessoaResponse;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoas.ObterPessoasRequest;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoas.ObterPessoasResponse;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoas.ObterPessoasResponse.PessoaResponse;
import xyz.xpto.gerenciamento.application.dtos.ErrorResponse;
import xyz.xpto.gerenciamento.application.services.PessoaService;

@RestController
@RequestMapping(value = "/api/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<PessoaResponse>> getPessoas() {
        ObterPessoasResponse response = pessoaService.obterPessoas(new ObterPessoasRequest());
        return ResponseEntity.ok(response.getPessoas());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPessoa(@PathVariable long id) {
        ObterPessoaResponse response = pessoaService.obterPessoa(new ObterPessoaRequest(id));
        if (response == null)
            return ResponseEntity.notFound().build();

        System.out.println("Response: " + response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postPessoa(@Valid @RequestBody CadastrarPessoaRequest request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult
                    .getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(new ErrorResponse(errors));
        }
        var response = pessoaService.cadastrarPessoa(request);
        return ResponseEntity.ok(response);
    }

}
