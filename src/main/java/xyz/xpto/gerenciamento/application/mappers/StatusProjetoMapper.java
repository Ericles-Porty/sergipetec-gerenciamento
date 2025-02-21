package xyz.xpto.gerenciamento.application.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.AtualizarStatusProjeto;
import xyz.xpto.gerenciamento.application.services.statusProjeto.dtos.CadastrarStatusProjeto;
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
                .statusProjetos(statusProjetos
                        .stream()
                        .map(s -> new ObterStatusProjetos.Response.StatusProjetoResponse(s.getId(), s.getNome()))
                        .toList())
                .build();
    }

    public CadastrarStatusProjeto.Response statusProjetoToCadastrarStatusProjetoResponse(StatusProjeto statusProjeto) {
        return new CadastrarStatusProjeto.Response(statusProjeto.getId(), statusProjeto.getNome());
    }

    public AtualizarStatusProjeto.Response statusProjetoToAtualizarStatusProjetoResponse(StatusProjeto statusProjeto) {
        return new AtualizarStatusProjeto.Response(statusProjeto.getId(), statusProjeto.getNome());
    }
}
