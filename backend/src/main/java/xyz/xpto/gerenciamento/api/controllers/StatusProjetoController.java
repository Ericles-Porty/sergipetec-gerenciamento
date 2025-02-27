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
import xyz.xpto.gerenciamento.api.extensions.StandardResponse;
import xyz.xpto.gerenciamento.api.extensions.ValidationExtension;
import xyz.xpto.gerenciamento.application.services.statusProjeto.StatusProjetoService;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.AtualizarStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.CadastrarStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.DeletarStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.ObterStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.ObterStatusProjetos;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/projetos/status", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatusProjetoController {

    private final StatusProjetoService statusProjetoService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getStatusProjetos() {
        ObterStatusProjetos.Response response = statusProjetoService.obterStatusProjetos(new ObterStatusProjetos.Request());
        return StandardResponse.success(response.statusProjetos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getStatusProjeto(@PathVariable Long id) {
        var response = statusProjetoService.obterStatusProjeto(new ObterStatusProjeto.Request(id));
        return StandardResponse.success(response);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postStatusProjeto(
            @Valid @RequestBody CadastrarStatusProjeto.Request request,
            BindingResult bindingResult) {
        ValidationExtension.validateBindingResult(bindingResult);

        var response = statusProjetoService.cadastrarStatusProjeto(request);
        return StandardResponse.created(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putStatusProjeto(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarStatusProjeto.Request request,
            BindingResult bindingResult) {
        ValidationExtension.validateBindingResult(bindingResult);

        var response = statusProjetoService.atualizarStatusProjeto(request);
        return StandardResponse.success(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteStatusProjeto(@PathVariable Long id) {
        statusProjetoService.deletarStatusProjeto(new DeletarStatusProjeto.Request(id));
        return StandardResponse.success();
    }

}
