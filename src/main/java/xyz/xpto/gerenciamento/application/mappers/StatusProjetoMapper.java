package xyz.xpto.gerenciamento.application.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.AtualizarStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.ObterStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.ObterStatusProjetos;
import xyz.xpto.gerenciamento.domain.entities.StatusProjeto;

@Component
public class StatusProjetoMapper {

    public ObterStatusProjeto.Response statusProjetoToObterStatusProjetoResponse(StatusProjeto statusProjeto) {
        return ObterStatusProjeto.Response.builder()
                .id(statusProjeto.getId())
                .nome(statusProjeto.getNome())
                .build();
    }

    public ObterStatusProjetos.Response statusProjetosToObterStatusProjetosResponse(
            List<StatusProjeto> statusProjetos) {
        return ObterStatusProjetos.Response.builder()
                .statusProjetoResponses(statusProjetos
                        .stream()
                        .map(s -> new ObterStatusProjetos.Response.StatusProjetoResponse(s.getId(), s.getNome()))
                        .toList())
                .build();
    }

    public AtualizarStatusProjeto.Response statusProjetoToAtualizarStatusProjetoResponse(StatusProjeto statusProjeto) {
        return new AtualizarStatusProjeto.Response(statusProjeto.getId(), statusProjeto.getNome());
    }
}
