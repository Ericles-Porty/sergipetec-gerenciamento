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
import xyz.xpto.gerenciamento.application.services.projeto.ProjetoService;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.AssociarProjetoEquipe;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.DesassociarProjetoEquipe;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.AtualizarProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.CadastrarProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.DeletarProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.ModificarStatusProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.ObterProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.ObterProjetos;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/projetos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjetoController {

	private final ProjetoService projetoService;

	@GetMapping(value = "/")
	public ResponseEntity<?> getProjetos() {
		ObterProjetos.Response response = projetoService.obterProjetos(new ObterProjetos.Request());
		return StandardResponse.success(response.projetos());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getProjeto(
			@PathVariable Long id) {
		ObterProjeto.Response response = projetoService.obterProjeto(new ObterProjeto.Request(id));
		return StandardResponse.success(response);
	}

	@PostMapping(value = "/")
	public ResponseEntity<?> postProjeto(@RequestBody @Valid CadastrarProjeto.Request request,
			BindingResult bindingResult) {
		ValidationExtension.validateBindingResult(bindingResult);

		var response = projetoService.cadastrarProjeto(request);
		return StandardResponse.created(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> putProjeto(@PathVariable Long id, @RequestBody @Valid AtualizarProjeto.Request request,
			BindingResult bindingResult) {
		ValidationExtension.validateBindingResult(bindingResult);
		if (id != request.id())
			return StandardResponse
					.badRequest("O id informado na URL é diferente do id informado no corpo da requisição.");

		var response = projetoService.atualizarProjeto(request);
		return StandardResponse.success(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProjeto(@PathVariable Long id) {
		var response = projetoService.deletarProjeto(new DeletarProjeto.Request(id));
		return StandardResponse.success(response);
	}

	@PutMapping(value = "/{id}/equipe/{idEquipe}")
	public ResponseEntity<?> associarProjetoEquipe(@PathVariable Long id, @PathVariable Long idEquipe) {
		var response = projetoService.associarProjetoEquipe(new AssociarProjetoEquipe.Request(id, idEquipe));
		return StandardResponse.success(response);
	}

	@DeleteMapping(value = "/{id}/equipe")
	public ResponseEntity<?> desassociarProjetoEquipe(@PathVariable Long id) {
		var response = projetoService.desassociarProjetoEquipe(new DesassociarProjetoEquipe.Request(id));
		return StandardResponse.success(response);
	}

	@PutMapping(value = "/{id}/status/{idStatus}")
	public ResponseEntity<?> putProjetoStatus(@PathVariable Long id, @PathVariable Long idStatus) {
		var response = projetoService.modificarStatusProjeto(new ModificarStatusProjeto.Request(id, idStatus));
		return StandardResponse.success(response);
	}

}
