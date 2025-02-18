package xyz.xpto.gerenciamento.infra.repositories.statusProjeto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import xyz.xpto.gerenciamento.domain.entities.StatusProjeto;

public interface StatusProjetoJpaRepository extends JpaRepository<StatusProjeto, Long> {

    @Query(value = "SELECT s FROM status_projeto s WHERE s.id = :id", nativeQuery = true)
    Optional<StatusProjeto> buscarPorId(Long id);

    @Query(value = "SELECT s FROM status_projeto s", nativeQuery = true)
    List<StatusProjeto> buscarTodos();

    @Modifying
    @Transactional
    @Query(value = "UPDATE status_projeto s SET s.nome = :nome WHERE s.id = :id", nativeQuery = true)
    void modificarNome(Long id, String nome);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM status_projeto s WHERE s.id = :id", nativeQuery = true)
    void deletar(Long id);

}
