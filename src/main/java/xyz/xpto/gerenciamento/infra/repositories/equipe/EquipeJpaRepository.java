package xyz.xpto.gerenciamento.infra.repositories.equipe;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import xyz.xpto.gerenciamento.domain.entities.Equipe;

@Repository
public interface EquipeJpaRepository extends JpaRepository<Equipe, Long> {

    @Query(value = "SELECT * FROM equipe e WHERE e.id = :id", nativeQuery = true)
    Optional<Equipe> buscarPorId(Long id);

    @Query(value = "SELECT * FROM equipe e", nativeQuery = true)
    List<Equipe> buscarTodos();

    @Modifying
    @Transactional
    @Query(value = "UPDATE equipe e SET e.nome = :nome WHERE e.id = :id", nativeQuery = true)
    Equipe modificarNome(Long id, String nome);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM equipe e WHERE e.id = :id", nativeQuery = true)
    void deletar(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pessoa p SET p.equipe_id = :idEquipe WHERE p.id = :idPessoa", nativeQuery = true)
    void adicionarPessoa(Long idEquipe, Long idPessoa);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pessoa p SET p.equipe_id = NULL WHERE p.id = :idPessoa", nativeQuery = true)
    void removerPessoa(Long idPessoa);

}
