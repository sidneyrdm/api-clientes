package io.github.sidneyrdm.clientes.rest;

import io.github.sidneyrdm.clientes.model.Cliente;
import io.github.sidneyrdm.clientes.model.Servico;
import io.github.sidneyrdm.clientes.repository.ClienteRepository;
import io.github.sidneyrdm.clientes.repository.ServicoRepository;
import io.github.sidneyrdm.clientes.util.BigdecimalConverter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

        private final ClienteRepository clienteRepository;
        private final ServicoRepository repository;
        private final BigdecimalConverter bigdecimalConverter;

    public ServicoController(ServicoRepository repository,
                             ClienteRepository clienteRepository,
                             BigdecimalConverter bigdecimalConverter) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.bigdecimalConverter = bigdecimalConverter;

    }

    @GetMapping("/list")
    public List<Servico> getServicos() {
        return repository.findAll();
    }

    @GetMapping
    public List<Servico> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes){
        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico saveServico(@RequestBody @Validated ServicoDTO dto ){

        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.BAD_REQUEST, "cliente inexistente."));
        Servico servico = new Servico();
        servico.setDescricao(dto.getDescricao());
        servico.setData(data);
        servico.setValor(bigdecimalConverter.converter(dto.getValor()));
        servico.setCliente(cliente);

        System.out.println(servico);
        return repository.save(servico);
    }
    @GetMapping("{id}")
    public Servico getElementByID(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServico(@PathVariable Integer id){
        repository
                .findById(id)
                .map(servico -> {
                    repository.delete(servico);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateServico(@PathVariable Integer id, @RequestBody @Validated Servico servicoAtualizado){
        repository
                .findById(id)
                .map(servico -> {
                    servico.setDescricao(servicoAtualizado.getDescricao());
                    servico.setValor(servicoAtualizado.getValor());
                    servico.setCliente(servicoAtualizado.getCliente());
                    return repository.save(servico);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
