package xyz.xpto.gerenciamento.infra.repositories.statusTarefa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xyz.xpto.gerenciamento.domain.entities.StatusTarefa;

public interface StatusTarefaJpaRepository extends JpaRepository<StatusTarefa, Long> {

    @Query(value = "SELECT s FROM status_tarefa s WHERE s.id = :id", nativeQuery = true)
    StatusTarefa buscarPorId(Long id);

    @Query(value = "SELECT s FROM status_tarefa s", nativeQuery = true)
    StatusTarefa buscarTodos();

    @Query(value = "UPDATE status_tarefa s SET s.nome = :nome WHERE s.id = :id", nativeQuery = true)
    void modificarNome(Long id, String nome);

    @Query(value = "DELETE FROM status_tarefa s WHERE s.id = :id", nativeQuery = true)
    void deletar(Long id);

}
