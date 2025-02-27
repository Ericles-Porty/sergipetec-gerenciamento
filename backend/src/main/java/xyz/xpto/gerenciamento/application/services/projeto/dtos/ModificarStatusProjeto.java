package xyz.xpto.gerenciamento.application.services.projeto.dtos;

public record ModificarStatusProjeto() {
    
    public record Request(Long idProjeto, Long idStatusProjeto) {}

    public record Response() {}
    
}
