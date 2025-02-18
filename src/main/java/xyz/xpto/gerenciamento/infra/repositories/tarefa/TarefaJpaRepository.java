package xyz.xpto.gerenciamento.infra.repositories.tarefa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import xyz.xpto.gerenciamento.domain.entities.Tarefa;

public interface TarefaJpaRepository extends JpaRepository<Tarefa, Long> {

    @Query(value = "SELECT t FROM tarefa t WHERE t.id = :id", nativeQuery = true)
    Optional<Tarefa> buscarPorId(Long id);

    @Query(value = "SELECT t FROM tarefa t", nativeQuery = true)
    Tarefa buscarTodos();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tarefa t WHERE t.id = :id", nativeQuery = true)
    void deletar(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tarefa t SET t.status_tarefa_id = :idStatusTarefa WHERE t.id = :id", nativeQuery = true)
    void modificarStatusTarefa(Long id, Long idStatusTarefa);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tarefa t SET t.pessoa_id = :idPessoa WHERE t.id = :id", nativeQuery = true)
    void associarPessoa(Long id, Long idPessoa);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tarefa t SET t.pessoa_id = NULL WHERE t.id = :id", nativeQuery = true)
    void desassociarPessoa(Long id);

}
