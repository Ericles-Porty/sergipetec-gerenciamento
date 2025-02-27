package xyz.xpto.gerenciamento.application.services.projeto;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import xyz.xpto.gerenciamento.application.interfaces.repositories.EquipeRepository;
import xyz.xpto.gerenciamento.application.interfaces.repositories.ProjetoRepository;
import xyz.xpto.gerenciamento.application.interfaces.repositories.StatusProjetoRepository;
import xyz.xpto.gerenciamento.application.mappers.ProjetoMapper;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.AssociarProjetoEquipe;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.AtualizarProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.CadastrarProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.DeletarProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.DesassociarProjetoEquipe;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.ModificarStatusProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.ObterProjeto;
import xyz.xpto.gerenciamento.application.services.projeto.dtos.ObterProjetos;
import xyz.xpto.gerenciamento.domain.entities.Equipe;
import xyz.xpto.gerenciamento.domain.entities.Projeto;
import xyz.xpto.gerenciamento.domain.entities.StatusProjeto;

@Service
@RequiredArgsConstructor
public class ProjetoService {

	private final ProjetoRepository repository;
	private final StatusProjetoRepository statusProjetoRepository;
	private final EquipeRepository equipeRepository;
	private final ProjetoMapper mapper;

	public ObterProjetos.Response obterProjetos(ObterProjetos.Request request) {
		return mapper.projetosToObterProjetosResponse(repository.buscarTodos());
	}

	public ObterProjeto.Response obterProjeto(ObterProjeto.Request request) {
		var projeto = repository.buscarPorId(request.id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));

		return mapper.projetoToObterProjetoResponse(projeto);
	}

	public CadastrarProjeto.Response cadastrarProjeto(CadastrarProjeto.Request request) {
		Projeto projeto = new Projeto();
		StatusProjeto statusProjeto = statusProjetoRepository.buscarPorId(1L)
				.orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status do projeto não encontrado"));
		projeto.setNome(request.nome());
		projeto.setDescricao(request.descricao());
		projeto.setDataInicio(Optional.ofNullable(request.dataInicio()).orElse(LocalDate.now()));
		projeto.setDataFim(Optional.ofNullable(request.dataFim()).orElse(LocalDate.now().plusYears(1L)));
		projeto.setStatusProjeto(statusProjeto);

		Projeto projetoCriado = repository.salvar(projeto);

		return mapper.projetoToCadastrarProjetoResponse(projetoCriado);
	}

	public AtualizarProjeto.Response atualizarProjeto(AtualizarProjeto.Request request) {
		Projeto projeto = repository.buscarPorId(request.id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));

		Optional.ofNullable(request.nome()).ifPresent(projeto::setNome);
		Optional.ofNullable(request.descricao()).ifPresent(projeto::setDescricao);
		Optional.ofNullable(request.dataInicio()).ifPresent(projeto::setDataInicio);
		Optional.ofNullable(request.dataFim()).ifPresent(projeto::setDataFim);

		Projeto projetoAtualizado = repository.salvar(projeto);

		return mapper.projetoToAtualizarProjetoResponse(projetoAtualizado);
	}

	public DeletarProjeto.Response deletarProjeto(DeletarProjeto.Request request) {
		Projeto projeto = repository.buscarPorId(request.id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));

		repository.deletar(projeto.getId());
		return new DeletarProjeto.Response();
	}

	public AssociarProjetoEquipe.Response associarProjetoEquipe(AssociarProjetoEquipe.Request request) {
		Projeto projeto = repository.buscarPorId(request.idProjeto())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));

		Equipe equipe = equipeRepository.buscarPorId(request.idEquipe())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe não encontrada"));

		projeto.setEquipe(equipe);

		repository.atualizar(request.idProjeto(), projeto);

		return new AssociarProjetoEquipe.Response();
	}

	public DesassociarProjetoEquipe.Response desassociarProjetoEquipe(DesassociarProjetoEquipe.Request request) {
		Projeto projeto = repository.buscarPorId(request.id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));

		projeto.setEquipe(null);

		repository.atualizar(projeto.getId(), projeto);
		return new DesassociarProjetoEquipe.Response();
	}

	public ModificarStatusProjeto.Response modificarStatusProjeto(ModificarStatusProjeto.Request request) {
		repository.buscarPorId(request.idProjeto())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));

		statusProjetoRepository.buscarPorId(request.idStatusProjeto())
				.orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status do projeto não encontrado"));

		repository.modificarStatusProjeto(request.idProjeto(), request.idStatusProjeto());

		return new ModificarStatusProjeto.Response();
	}
}
