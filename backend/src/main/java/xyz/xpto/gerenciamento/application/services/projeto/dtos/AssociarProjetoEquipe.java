package xyz.xpto.gerenciamento.application.services.projeto.dtos;

public record AssociarProjetoEquipe() {

    public record Request(Long idProjeto, Long idEquipe) {}

    public record Response() {}
}
