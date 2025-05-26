package aplicacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.model.HistorialEvaluacion;

import java.util.List;

public interface HistorialEvaluacionRepository extends JpaRepository<HistorialEvaluacion, Long> {
    List<HistorialEvaluacion> findByClienteId(Long clienteId);
}
