package xyz.xpto.gerenciamento.api.controllers;

import xyz.xpto.gerenciamento.api.extensions.StandardResponse;
import xyz.xpto.gerenciamento.api.extensions.ValidationExtension;
import xyz.xpto.gerenciamento.application.services.equipe.EquipeService;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.ApagarEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.AtualizarEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.CadastrarEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.ObterEquipe;
import xyz.xpto.gerenciamento.application.services.equipe.dtos.ObterEquipes;

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
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/equipes", produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipeController {

    private final EquipeService equipeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterEquipe(
            @PathVariable @Valid @Min(value = 1, message = "O id da equipe é obrigatório e deve ser maior que zero.") Long id) {
        var response = equipeService.obterEquipe(new ObterEquipe.Request(id));
        return StandardResponse.success(response);
    }

    @GetMapping(value = "")
    public ResponseEntity<?> obterEquipes() {
        var response = equipeService.obterEquipes(new ObterEquipes.Request());
        return StandardResponse.success(response.equipes());
    }

    @PostMapping(value = "")
    public ResponseEntity<?> cadastrarEquipe(@RequestBody @Valid CadastrarEquipe.Request request,
            BindingResult bindingResult) {
        ValidationExtension.validateBindingResult(bindingResult);
        var response = equipeService.cadastrarEquipe(request);
        return StandardResponse.success(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarEquipe(
            @PathVariable @Valid @Min(value = 1, message = "O id da equipe é obrigatório e deve ser maior que zero.") Long id,
            @RequestBody @Valid AtualizarEquipe.Request request, BindingResult bindingResult) {
        ValidationExtension.validateBindingResult(bindingResult);
        var response = equipeService.atualizarEquipe(id, request);
        return StandardResponse.success(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> apagarEquipe(
            @PathVariable @Valid @Min(value = 1, message = "O id da equipe é obrigatório e deve ser maior que zero.") Long id) {
        equipeService.apagarEquipe(new ApagarEquipe.Request(id));
        return StandardResponse.success();
    }

}
