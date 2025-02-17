package xyz.xpto.gerenciamento.application.services.pessoa.dtos;

public record AtualizarPessoa() {
    public record Request(long id, String nome) {}

    public record Response(long id, String nome) {}
}
