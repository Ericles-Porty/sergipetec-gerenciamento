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
import xyz.xpto.gerenciamento.application.services.tarefa.TarefaService;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.ObterTarefas;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.ObterTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.CadastrarTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.AtualizarTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.DeletarTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.ModificarStatusTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.AssociarTarefaPessoa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.DesassociarTarefaPessoa;
import xyz.xpto.gerenciamento.api.extensions.ValidationExtension;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/tarefas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TarefaController {

	private final TarefaService tarefaService;

	@GetMapping(value = "/")
	public ResponseEntity<?> getTarefas() {
		ObterTarefas.Response response = tarefaService.obterTarefas(new ObterTarefas.Request());
		return StandardResponse.success(response.tarefas());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getTarefa(
			@PathVariable Long id) {
		ObterTarefa.Response response = tarefaService.obterTarefa(new ObterTarefa.Request(id));
		return StandardResponse.success(response);
	}

	@PostMapping(value = "/")
	public ResponseEntity<?> postTarefa(@RequestBody @Valid CadastrarTarefa.Request request,
			BindingResult bindingResult) {
		ValidationExtension.validateBindingResult(bindingResult);

		var response = tarefaService.cadastrarTarefa(request);
		return StandardResponse.created(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> putTarefa(@PathVariable Long id, @RequestBody @Valid AtualizarTarefa.Request request,
			BindingResult bindingResult) {
		ValidationExtension.validateBindingResult(bindingResult);

		var response = tarefaService.atualizarTarefa(request);
		return StandardResponse.success(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteTarefa(@PathVariable Long id) {
		var response = tarefaService.deletarTarefa(new DeletarTarefa.Request(id));
		return StandardResponse.success(response);
	}

	@PutMapping(value = "/{id}/status/{idStatus}")
	public ResponseEntity<?> putStatusTarefa(@PathVariable Long id, @PathVariable Long idStatus) {
		var response = tarefaService.modificarStatusTarefa(new ModificarStatusTarefa.Request(id, idStatus));
		return StandardResponse.success(response);
	}

	@PutMapping(value = "/{id}/pessoa/{idPessoa}")
	public ResponseEntity<?> putPessoaTarefa(@PathVariable Long id, @PathVariable Long idPessoa) {
		var response = tarefaService.associarTarefaPessoa(new AssociarTarefaPessoa.Request(id, idPessoa));
		return StandardResponse.success(response);
	}

	@DeleteMapping(value = "/{id}/pessoa")
	public ResponseEntity<?> deletePessoaTarefa(@PathVariable Long id) {
		var response = tarefaService.desassociarTarefaPessoa(new DesassociarTarefaPessoa.Request(id));
		return StandardResponse.success(response);
	}

}
