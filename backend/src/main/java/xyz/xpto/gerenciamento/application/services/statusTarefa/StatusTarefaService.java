package xyz.xpto.gerenciamento.application.services.statusTarefa;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import xyz.xpto.gerenciamento.application.interfaces.repositories.StatusTarefaRepository;
import xyz.xpto.gerenciamento.application.mappers.StatusTarefaMapper;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.AtualizarStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.CadastrarStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.DeletarStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.ObterStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.ObterStatusTarefas;
import xyz.xpto.gerenciamento.domain.entities.StatusTarefa;

@Service
@RequiredArgsConstructor
public class StatusTarefaService {

    private final StatusTarefaRepository repository;
    private final StatusTarefaMapper mapper;

    public ObterStatusTarefas.Response obterStatusTarefas(ObterStatusTarefas.Request request) {
        var statusTarefas = repository.buscarTodos();

        return mapper.statusTarefasToObterStatusTarefasResponse(statusTarefas);
    }

    public ObterStatusTarefa.Response obterStatusTarefa(ObterStatusTarefa.Request request) {
        var statusTarefa = repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status de Tarefa não encontrado"));

        return mapper.statusTarefaToObterStatusTarefaResponse(statusTarefa);
    }

    public CadastrarStatusTarefa.Response cadastrarStatusTarefa(CadastrarStatusTarefa.Request request) {
        var statusTarefa = new StatusTarefa();
        statusTarefa.setNome(request.nome());

        var statusTarefaCriado = repository.salvar(statusTarefa);

        return mapper.statusTarefaToCadastrarStatusTarefaResponse(statusTarefaCriado);
    }

    public DeletarStatusTarefa.Response deletarStatusTarefa(DeletarStatusTarefa.Request request) {
        var statusTarefa = repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status de Tarefa não encontrado"));

        repository.deletar(statusTarefa.getId());
        return new DeletarStatusTarefa.Response();
    }

    public AtualizarStatusTarefa.Response atualizarStatusTarefa(AtualizarStatusTarefa.Request request) {
        var statusTarefa = repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status de Tarefa não encontrado"));

        statusTarefa.setNome(request.nome());
        var statusTarefaAtualizado = repository.salvar(statusTarefa);

        return new AtualizarStatusTarefa.Response(statusTarefaAtualizado.getId(), statusTarefaAtualizado.getNome());
    }
}
