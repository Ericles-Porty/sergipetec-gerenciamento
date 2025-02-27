package xyz.xpto.gerenciamento.application.services.tarefa;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import xyz.xpto.gerenciamento.application.interfaces.repositories.PessoaRepository;
import xyz.xpto.gerenciamento.application.interfaces.repositories.ProjetoRepository;
import xyz.xpto.gerenciamento.application.interfaces.repositories.StatusTarefaRepository;
import xyz.xpto.gerenciamento.application.interfaces.repositories.TarefaRepository;
import xyz.xpto.gerenciamento.application.mappers.TarefaMapper;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.ObterTarefas;
import xyz.xpto.gerenciamento.domain.entities.Pessoa;
import xyz.xpto.gerenciamento.domain.entities.Projeto;
import xyz.xpto.gerenciamento.domain.entities.StatusTarefa;
import xyz.xpto.gerenciamento.domain.entities.Tarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.ObterTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.CadastrarTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.DeletarTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.DesassociarTarefaPessoa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.ModificarStatusTarefa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.AssociarTarefaPessoa;
import xyz.xpto.gerenciamento.application.services.tarefa.dtos.AtualizarTarefa;

@Service
@RequiredArgsConstructor
public class TarefaService {

	private final TarefaRepository repository;
	private final StatusTarefaRepository statusTarefaRepository;
	private final PessoaRepository pessoaRepository;
	private final ProjetoRepository projetoRepository;
	private final TarefaMapper mapper;

	public ObterTarefas.Response obterTarefas(ObterTarefas.Request request) {
		return mapper.tarefasToObterTarefasResponse(repository.buscarTodos());
	}

	public ObterTarefa.Response obterTarefa(ObterTarefa.Request request) {
		var tarefa = repository.buscarPorId(request.id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

		return mapper.tarefaToObterTarefaResponse(tarefa);
	}

	public CadastrarTarefa.Response cadastrarTarefa(CadastrarTarefa.Request request) {
		Tarefa tarefa = new Tarefa();

		tarefa.setTitulo(request.titulo());
		tarefa.setDescricao(request.descricao());

		StatusTarefa statusTarefa = statusTarefaRepository
				.buscarPorId(1L)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Status da tarefa padrão não encontrado"));
		tarefa.setStatusTarefa(statusTarefa);

		Projeto projeto = projetoRepository.buscarPorId(request.idProjeto())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));
		tarefa.setProjeto(projeto);

		Optional.ofNullable(request.idPessoaResponsavel())
				.ifPresent(idPessoa -> tarefa.setResponsavel(pessoaRepository.buscarPorId(idPessoa)
						.orElseThrow(
								() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"))));

		Tarefa tarefaCriada = repository.salvar(tarefa);

		return mapper.tarefaToCadastrarTarefaResponse(tarefaCriada);
	}

	public AtualizarTarefa.Response atualizarTarefa(AtualizarTarefa.Request request) {
		Tarefa tarefa = repository.buscarPorId(request.id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

		Optional.ofNullable(request.titulo()).ifPresent(tarefa::setTitulo);
		Optional.ofNullable(request.descricao()).ifPresent(tarefa::setDescricao);

		Optional.ofNullable(request.idPessoaResponsavel())
				.ifPresent(idPessoa -> tarefa.setResponsavel(
						pessoaRepository.buscarPorId(idPessoa)
								.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
										"Pessoa não encontrada"))));

		Optional.ofNullable(request.idStatusTarefa())
				.ifPresent(idStatus -> tarefa.setStatusTarefa(
						statusTarefaRepository.buscarPorId(idStatus)
								.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
										"Status da tarefa não encontrado"))));

		Tarefa tarefaAtualizada = repository.salvar(tarefa);

		return mapper.tarefaToAtualizarTarefaResponse(tarefaAtualizada);
	}

	public DeletarTarefa.Response deletarTarefa(DeletarTarefa.Request request) {
		Tarefa tarefa = repository.buscarPorId(request.id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

		repository.deletar(tarefa.getId());
		return new DeletarTarefa.Response();
	}

	public ModificarStatusTarefa.Response modificarStatusTarefa(ModificarStatusTarefa.Request request) {
		Tarefa tarefa = repository.buscarPorId(request.idStatusTarefa())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

		StatusTarefa statusTarefa = statusTarefaRepository.buscarPorId(request.idStatusTarefa())
				.orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status da tarefa não encontrado"));

		tarefa.setStatusTarefa(statusTarefa);

		repository.salvar(tarefa);
		return new ModificarStatusTarefa.Response();

	}

	public AssociarTarefaPessoa.Response associarTarefaPessoa(AssociarTarefaPessoa.Request request) {
		Tarefa tarefa = repository.buscarPorId(request.idTarefa())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

		Pessoa pessoa = pessoaRepository.buscarPorId(request.idPessoa())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

		tarefa.setResponsavel(pessoa);

		repository.salvar(tarefa);

		return new AssociarTarefaPessoa.Response();
	}

	public DesassociarTarefaPessoa.Response desassociarTarefaPessoa(DesassociarTarefaPessoa.Request request) {
		Tarefa tarefa = repository.buscarPorId(request.id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

		tarefa.setResponsavel(null);

		repository.salvar(tarefa);

		return new DesassociarTarefaPessoa.Response();
	}
}