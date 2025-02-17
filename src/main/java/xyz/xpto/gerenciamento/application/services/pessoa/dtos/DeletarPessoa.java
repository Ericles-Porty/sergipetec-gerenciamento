package xyz.xpto.gerenciamento.application.services.pessoa.dtos;

public record DeletarPessoa() {
    public record Request(long id) {}

    public record Response() {}
}
