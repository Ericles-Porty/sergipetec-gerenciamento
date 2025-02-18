package xyz.xpto.gerenciamento.application.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import xyz.xpto.gerenciamento.application.interfaces.repositories.PessoaRepository;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.AtualizarPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.CadastrarPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.DeletarPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.ObterPessoa;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.ObterPessoas;
import xyz.xpto.gerenciamento.domain.entities.Pessoa;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository repository;
    private final PessoaMapper mapper;

    public ObterPessoas.Response obterPessoas(ObterPessoas.Request request) {
        var pessoas = repository.buscarTodos();

        var response = pessoas.stream()
                .map(mapper::toResponse)
                .toList();

        return new ObterPessoas.Response(response);
    }

    public ObterPessoa.Response obterPessoa(ObterPessoa.Request request) {
        var pessoa = repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        var equipe = pessoa.getEquipe() != null
                ? new ObterPessoa.Response.EquipeResponse(pessoa.getEquipe().getId(), pessoa.getEquipe().getNome())
                : null;

        var response = new ObterPessoa.Response(pessoa.getId(), pessoa.getNome(), equipe);
        return response;
    }

    public CadastrarPessoa.Response cadastrarPessoa(CadastrarPessoa.Request request) {
        var pessoa = new Pessoa();
        pessoa.setNome(request.nome());
        var pessoaCriada = repository.salvar(pessoa);
        var response = new CadastrarPessoa.Response(pessoaCriada.getId(), pessoaCriada.getNome());
        return response;
    }

    public DeletarPessoa.Response deletarPessoa(DeletarPessoa.Request request) {
        repository.deletar(request.id());
        return new DeletarPessoa.Response();
    }

    public AtualizarPessoa.Response atualizarPessoa(AtualizarPessoa.Request request) {
        var pessoa = repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        pessoa.setNome(request.nome());
        var pessoaAtualizada = repository.salvar(pessoa);

        return new AtualizarPessoa.Response(pessoaAtualizada.getId(), pessoaAtualizada.getNome());
    }
}
