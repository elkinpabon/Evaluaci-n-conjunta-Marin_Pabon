package aplicacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
