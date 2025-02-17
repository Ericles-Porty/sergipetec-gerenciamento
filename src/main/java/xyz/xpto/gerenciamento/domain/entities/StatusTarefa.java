package xyz.xpto.gerenciamento.domain.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status_tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusTarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tarefa_id_sequence")
    @SequenceGenerator(name = "tarefa_id_sequence", sequenceName = "tarefa_id_sequence", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "statusTarefa", targetEntity = Tarefa.class)
    private List<Tarefa> tarefas;
}
