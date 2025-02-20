package xyz.xpto.gerenciamento.application.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import xyz.xpto.gerenciamento.application.interfaces.repositories.PessoaRepository;
import xyz.xpto.gerenciamento.application.mappers.PessoaMapper;
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

        return mapper.pessoaToObterPessoasResponse(pessoas);
    }

    public ObterPessoa.Response obterPessoa(ObterPessoa.Request request) {
        var pessoa = repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        return mapper.pessoaToObterPessoaResponse(pessoa);
    }

    public CadastrarPessoa.Response cadastrarPessoa(CadastrarPessoa.Request request) {
        var pessoa = new Pessoa();
        pessoa.setNome(request.nome());

        var pessoaCriada = repository.salvar(pessoa);

        return mapper.pessoaToCadastrarPessoaResponse(pessoaCriada);
    }

    public DeletarPessoa.Response deletarPessoa(DeletarPessoa.Request request) {
        repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

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
