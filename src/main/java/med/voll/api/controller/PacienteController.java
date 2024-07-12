package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPacientes(@RequestBody @Valid CadastroPacientes dados, UriComponentsBuilder uriBuilder) {
        var pacientes = new Pacientes(dados);
        repository.save(pacientes);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacientes.getId()).toUri();
        return ResponseEntity.created(uri).body(pacientes);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaPacientes>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao)
                .map(DadosListaPacientes::new);

        return ResponseEntity.ok(page);
    }

    //atualização dos objetos paciente
    @PutMapping
    @Transactional
    public ResponseEntity atualizarPacientes(@RequestBody @Valid AtualizarPacientes dados) {
        var pacientes = repository.getReferenceById(dados.id());
        pacientes.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DetalhamentoPaciente(pacientes));
    }

    //desativar objetos paciente no banco de dados
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPacientes(@PathVariable Long id) {
        var pacientes = repository.getReferenceById(id);
        pacientes.excluirPacientes();

        return ResponseEntity.noContent().build();

    }

}
