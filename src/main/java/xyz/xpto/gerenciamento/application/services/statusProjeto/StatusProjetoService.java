package xyz.xpto.gerenciamento.application.services.statusProjeto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import xyz.xpto.gerenciamento.application.interfaces.repositories.StatusProjetoRepository;
import xyz.xpto.gerenciamento.application.mappers.StatusProjetoMapper;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.AtualizarStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.CadastrarStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.DeletarStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.ObterStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.ObterStatusProjetos;
import xyz.xpto.gerenciamento.domain.entities.StatusProjeto;

@Service
@RequiredArgsConstructor
public class StatusProjetoService {

    private final StatusProjetoRepository repository;
    private final StatusProjetoMapper mapper;

    public ObterStatusProjetos.Response obterStatusProjetos(ObterStatusProjetos.Request request) {
        var statusProjetos = repository.buscarTodos();

        return mapper.statusProjetosToObterStatusProjetosResponse(statusProjetos);
    }

    public ObterStatusProjeto.Response obterStatusProjeto(ObterStatusProjeto.Request request) {
        var statusProjeto = repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status de Projeto não encontrado"));

        return mapper.statusProjetoToObterStatusProjetoResponse(statusProjeto);
    }

    public CadastrarStatusProjeto.Response cadastrarStatusProjeto(CadastrarStatusProjeto.Request request) {
        var statusProjeto = new StatusProjeto();
        statusProjeto.setNome(request.nome());

        var statusProjetoCriado = repository.salvar(statusProjeto);

        return mapper.statusProjetoToCadastrarStatusProjetoResponse(statusProjetoCriado);
    }

    public DeletarStatusProjeto.Response deletarStatusProjeto(DeletarStatusProjeto.Request request) {
        var statusProjeto = repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status de Projeto não encontrado"));

        repository.deletar(statusProjeto.getId());
        return new DeletarStatusProjeto.Response();
    }

    public AtualizarStatusProjeto.Response atualizarStatusProjeto(AtualizarStatusProjeto.Request request) {
        var statusProjeto = repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status de Projeto não encontrado"));

        statusProjeto.setNome(request.nome());
        var statusProjetoAtualizado = repository.salvar(statusProjeto);

        return new AtualizarStatusProjeto.Response(statusProjetoAtualizado.getId(), statusProjetoAtualizado.getNome());
    }
}
