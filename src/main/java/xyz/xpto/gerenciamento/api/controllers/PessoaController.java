package xyz.xpto.gerenciamento.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import xyz.xpto.gerenciamento.application.services.pessoa.PessoaService;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.AtualizarPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.CadastrarPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.DeletarPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.ObterPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.ObterPessoas;
import xyz.xpto.gerenciamento.api.extensions.StandardResponse;
import xyz.xpto.gerenciamento.api.extensions.ValidationExtension;

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
        return StandardResponse.success(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putPessoa(@PathVariable long id, @Valid @RequestBody AtualizarPessoa.Request request) {
        if (id != request.id()) {
            return StandardResponse
                    .badRequest("O id informado na URL é diferente do id informado no corpo da requisição.");
        }
        var response = pessoaService.atualizarPessoa(request);
        return StandardResponse.success(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePessoa(@PathVariable long id) {
        pessoaService.deletarPessoa(new DeletarPessoa.Request(id));
        return StandardResponse.success();
    }

}
