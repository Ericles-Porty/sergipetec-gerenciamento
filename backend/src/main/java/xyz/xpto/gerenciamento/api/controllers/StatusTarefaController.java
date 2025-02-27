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
import xyz.xpto.gerenciamento.application.services.statusTarefa.StatusTarefaService;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.AtualizarStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.CadastrarStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.DeletarStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.ObterStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.ObterStatusTarefas;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/tarefas/status", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatusTarefaController {

    private final StatusTarefaService statusTarefaService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getStatusTarefas() {
        ObterStatusTarefas.Response response = statusTarefaService.obterStatusTarefas(new ObterStatusTarefas.Request());
        return StandardResponse.success(response.statusTarefas());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getStatusTarefa(@PathVariable Long id) {
        var response = statusTarefaService.obterStatusTarefa(new ObterStatusTarefa.Request(id));
        return StandardResponse.success(response);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postStatusTarefa(
            @Valid @RequestBody CadastrarStatusTarefa.Request request,
            BindingResult bindingResult) {
        ValidationExtension.validateBindingResult(bindingResult);

        var response = statusTarefaService.cadastrarStatusTarefa(request);
        return StandardResponse.created(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putStatusTarefa(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarStatusTarefa.Request request,
            BindingResult bindingResult) {
        ValidationExtension.validateBindingResult(bindingResult);

        var response = statusTarefaService.atualizarStatusTarefa(request);
        return StandardResponse.success(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteStatusTarefa(@PathVariable Long id) {
        statusTarefaService.deletarStatusTarefa(new DeletarStatusTarefa.Request(id));
        return StandardResponse.success();
    }

}
