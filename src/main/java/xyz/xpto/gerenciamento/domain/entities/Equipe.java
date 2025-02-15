package xyz.xpto.gerenciamento.domain.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
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
@Getter
@Setter
@AllArgsConstructor
@Table(name = "equipe")
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "equipe_id_sequence")
    @SequenceGenerator(name = "equipe_id_sequence", sequenceName = "equipe_id_sequence", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "equipe", targetEntity = Pessoa.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Pessoa> pessoas;

}
