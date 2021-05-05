package it.prova.gestionepizzeria.repository.cliente;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepizzeria.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
