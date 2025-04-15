package io.github.sidneyrdm.clientes.rest;

import io.github.sidneyrdm.clientes.model.Usuario;
import io.github.sidneyrdm.clientes.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Usuario> getusuarios() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUsuario(@RequestBody @Validated Usuario usuario ){
         repository.save(usuario);
    }
    @GetMapping("{id}")
    public Usuario getElementByID(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable Integer id){
        repository
                .findById(id)
                .map(usuario -> {
                    repository.delete(usuario);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUsuario(@PathVariable Integer id, @RequestBody @Validated Usuario usuarioAtualizado){
        repository
                .findById(id)
                .map(usuario -> {

                    usuario.setUserName(usuarioAtualizado.getUserName());
                    usuario.setPassword(usuarioAtualizado.getPassword());
                    if(usuario.getUserName() != "" && usuario.getUserName() != null){
                        return repository.save(usuario);
                    } else return null;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
