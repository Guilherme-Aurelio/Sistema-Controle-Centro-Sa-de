package CS.CS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import CS.CS.domain.paciente.Paciente;
import CS.CS.repository.PacienteRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("paciente")
public class PacienteController {
    
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Paciente paciente,
            UriComponentsBuilder uriBuilder) {
        Paciente pacienteLocal = repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> listar(@PageableDefault(size = 4, sort = { "nome" }) Pageable paginacao) {
        var pacientes = repository.findAll(paginacao);
        return ResponseEntity.ok(pacientes);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        repository.delete(paciente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Paciente> atualizar(@RequestBody @Valid Paciente paciente) {
        Paciente pacienteLocal = repository.findById(paciente.getId()).get();

        pacienteLocal.setNome(paciente.getNome());

        return ResponseEntity.ok(pacienteLocal);
    }

}
