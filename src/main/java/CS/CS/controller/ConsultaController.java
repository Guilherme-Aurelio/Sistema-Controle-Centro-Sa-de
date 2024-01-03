package CS.CS.controller;

import java.util.Optional;

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

import CS.CS.domain.consulta.Consulta;
import CS.CS.repository.ConsultaRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("consulta")
public class ConsultaController {
    
    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Consulta consulta,
            UriComponentsBuilder uriBuilder) {
        Consulta consultaLocal = repository.save(consulta);
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consultaLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {
        var consulta = repository.getReferenceById(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping
    public ResponseEntity<Page<Consulta>> listar(@PageableDefault(size = 4, sort = { "id" }) Pageable paginacao) {
        var consultas = repository.findAll(paginacao);
        return ResponseEntity.ok(consultas);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var consulta = repository.getReferenceById(id);
        repository.delete(consulta);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody @Valid Consulta consulta) {
        Optional<Consulta> optionalConsulta = repository.findById(id);

        if (optionalConsulta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Consulta consultaLocal = optionalConsulta.get();

        consultaLocal.setDataConsulta(consulta.getDataConsulta());
        consultaLocal.setDiagnostico(consulta.getDiagnostico());
        consultaLocal.setPrescricao(consulta.getPrescricao());
        consultaLocal.setPaciente(consulta.getPaciente());
        consultaLocal.setMedico(consulta.getMedico());

        Consulta consultaAtualizada = repository.save(consultaLocal);
        return ResponseEntity.ok(consultaAtualizada);
    }

}
