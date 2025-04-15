package io.github.sidneyrdm.clientes.repository;

import io.github.sidneyrdm.clientes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
