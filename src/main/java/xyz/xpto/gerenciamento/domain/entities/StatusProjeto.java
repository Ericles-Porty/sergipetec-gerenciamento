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
import lombok.Setter;

@Entity
@Table(name = "status_projeto")
@Getter
@Setter
@AllArgsConstructor
public class StatusProjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "status_projeto_id_sequence")
    @SequenceGenerator(name = "status_projeto_id_sequence", sequenceName = "status_projeto_id_sequence", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "statusProjeto", targetEntity = Projeto.class)
    private List<Projeto> projetos;

}
