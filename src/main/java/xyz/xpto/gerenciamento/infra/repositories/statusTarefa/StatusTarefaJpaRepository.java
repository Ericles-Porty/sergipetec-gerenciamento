package xyz.xpto.gerenciamento.infra.repositories.statusTarefa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xyz.xpto.gerenciamento.domain.entities.StatusTarefa;

@Repository
public interface StatusTarefaJpaRepository extends JpaRepository<StatusTarefa, Long> {

    @Query(value = "SELECT * FROM status_tarefa s WHERE s.id = :id", nativeQuery = true)
    Optional<StatusTarefa> buscarPorId(Long id);

    @Query(value = "SELECT * FROM status_tarefa s", nativeQuery = true)
    List<StatusTarefa> buscarTodos();

    @Query(value = "UPDATE status_tarefa s SET s.nome = :nome WHERE s.id = :id", nativeQuery = true)
    void modificarNome(Long id, String nome);

    @Query(value = "DELETE FROM status_tarefa s WHERE s.id = :id", nativeQuery = true)
    void deletar(Long id);

}
