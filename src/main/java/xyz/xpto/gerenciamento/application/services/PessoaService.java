package xyz.xpto.gerenciamento.application.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import xyz.xpto.gerenciamento.application.interfaces.repositories.PessoaRepository;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.cadastrarPessoa.CadastrarPessoaRequest;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.cadastrarPessoa.CadastrarPessoaResponse;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoa.ObterPessoaRequest;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoa.ObterPessoaResponse;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoas.ObterPessoasRequest;
import xyz.xpto.gerenciamento.application.services.pessoa.dtos.obterPessoas.ObterPessoasResponse;
import xyz.xpto.gerenciamento.domain.entities.Pessoa;

@Service
public class PessoaService {
    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public ObterPessoasResponse obterPessoas(ObterPessoasRequest request) {
        var pessoas = repository.buscarTodos();

        var response = pessoas.stream()
                .map(pessoa -> {
                    var equipe = pessoa.getEquipe() != null
                            ? new ObterPessoasResponse.EquipeResponse(pessoa.getEquipe().getId(),
                                    pessoa.getEquipe().getNome())
                            : null;

                    return new ObterPessoasResponse.PessoaResponse(pessoa.getId(), pessoa.getNome(), equipe);
                })
                .toList();

        return new ObterPessoasResponse(response);
    }

    public ObterPessoaResponse obterPessoa(ObterPessoaRequest request) {
        var pessoa = repository.buscarPorId(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        var equipe = pessoa.getEquipe() != null
                ? new ObterPessoaResponse.EquipeResponse(pessoa.getEquipe().getId(), pessoa.getEquipe().getNome())
                : null;

        var response = new ObterPessoaResponse(pessoa.getId(), pessoa.getNome(), equipe);
        return response;
    }

    public CadastrarPessoaResponse cadastrarPessoa(CadastrarPessoaRequest request) {
        var pessoa = new Pessoa();
        pessoa.setNome(request.getNome());
        var pessoaCriada = repository.salvar(pessoa);
        var response = new CadastrarPessoaResponse(pessoaCriada.getId(), pessoaCriada.getNome());
        return response;
    }
}
