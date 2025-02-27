package xyz.xpto.gerenciamento.infra.repositories.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import xyz.xpto.gerenciamento.domain.entities.Pessoa;

@Repository
public interface PessoaJpaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "SELECT * FROM pessoa p WHERE p.id = :id", nativeQuery = true)
    Optional<Pessoa> buscarPorId(Long id);

    @Query(value = "SELECT * FROM pessoa p", nativeQuery = true)
    List<Pessoa> buscarTodos();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pessoa p WHERE p.id = :id", nativeQuery = true)
    void deletar(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pessoa p SET p.equipe_id = :idEquipe WHERE p.id = :id", nativeQuery = true)
    void associarEquipe(Long id, Long idEquipe);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pessoa p SET p.equipe_id = NULL WHERE p.id = :id", nativeQuery = true)
    void desassociarEquipe(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pessoa p SET p.nome = :nome WHERE p.id = :id", nativeQuery = true)
    void modificarNome(Long id, String nome);

}
