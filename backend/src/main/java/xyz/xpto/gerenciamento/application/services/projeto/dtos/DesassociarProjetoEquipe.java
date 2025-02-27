package xyz.xpto.gerenciamento.application.services.projeto.dtos;

public record DesassociarProjetoEquipe() {

    public record Request(Long id) {}

    public record Response() {}
}
