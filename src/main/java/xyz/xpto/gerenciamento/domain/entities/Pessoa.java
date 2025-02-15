package xyz.xpto.gerenciamento.domain.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pessoa_id_sequence")
    @SequenceGenerator(name = "pessoa_id_sequence", sequenceName = "pessoa_id_sequence", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "equipe_id", nullable = true)
    private Equipe equipe;

    @OneToMany(mappedBy = "pessoa", targetEntity = Tarefa.class)
    private List<Tarefa> tarefas;
}
