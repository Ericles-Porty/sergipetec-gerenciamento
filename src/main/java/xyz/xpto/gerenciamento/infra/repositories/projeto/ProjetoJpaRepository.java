package xyz.xpto.gerenciamento.infra.repositories.projeto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import xyz.xpto.gerenciamento.domain.entities.Projeto;

@Repository
public interface ProjetoJpaRepository extends JpaRepository<Projeto, Long> {

    @Query(value = "SELECT p FROM projeto p WHERE p.id = :id", nativeQuery = true)
    Projeto buscarPorId(Long id);

    @Query(value = "SELECT p FROM projeto p", nativeQuery = true)
    Projeto buscarTodos();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM projeto p WHERE p.id = :id", nativeQuery = true)
    void deletar(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE projeto p SET p.status_projeto_id = :idStatusProjeto WHERE p.id = :id", nativeQuery = true)
    void modificarStatusProjeto(Long id, Long idStatusProjeto);

    @Modifying
    @Transactional
    @Query(value = "UPDATE projeto SET equipe_id = :idEquipe WHERE id = :id", nativeQuery = true)
    void associarEquipe(Long id, Long idEquipe);

    @Modifying
    @Transactional
    @Query(value = "UPDATE projeto SET equipe_id = NULL WHERE id = :idProjeto", nativeQuery = true)
    void desassociarEquipe(Long idProjeto);
}
