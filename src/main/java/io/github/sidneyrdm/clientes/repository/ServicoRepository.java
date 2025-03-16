package io.github.sidneyrdm.clientes.repository;

import io.github.sidneyrdm.clientes.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    
}
