package xyz.xpto.gerenciamento.application.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.AtualizarStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.CadastrarStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.ObterStatusTarefa;
import xyz.xpto.gerenciamento.application.services.statusTarefa.dtos.ObterStatusTarefas;
import xyz.xpto.gerenciamento.domain.entities.StatusTarefa;

@Component
public class StatusTarefaMapper {

    public ObterStatusTarefa.Response statusTarefaToObterStatusTarefaResponse(StatusTarefa statusTarefa) {
        return ObterStatusTarefa.Response.builder()
                .id(statusTarefa.getId())
                .nome(statusTarefa.getNome())
                .build();
    }

    public ObterStatusTarefas.Response statusTarefasToObterStatusTarefasResponse(
            List<StatusTarefa> statusTarefas) {
        return ObterStatusTarefas.Response.builder()
                .statusTarefas(statusTarefas
                        .stream()
                        .map(s -> new ObterStatusTarefas.Response.StatusTarefaResponse(s.getId(), s.getNome()))
                        .toList())
                .build();
    }

    public CadastrarStatusTarefa.Response statusTarefaToCadastrarStatusTarefaResponse(StatusTarefa statusTarefa) {
        return new CadastrarStatusTarefa.Response(statusTarefa.getId(), statusTarefa.getNome());
    }

    public AtualizarStatusTarefa.Response statusTarefaToAtualizarStatusTarefaResponse(StatusTarefa statusTarefa) {
        return new AtualizarStatusTarefa.Response(statusTarefa.getId(), statusTarefa.getNome());
    }
}
