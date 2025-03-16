package io.github.sidneyrdm.clientes.repository;

import io.github.sidneyrdm.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
