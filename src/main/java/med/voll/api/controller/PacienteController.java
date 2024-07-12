package med.voll.api.controller;



import jakarta.validation.Valid;
import med.voll.api.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPacientes(@RequestBody @Valid CadastroPacientes dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListaPacientes> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(DadosListaPacientes::new);

    }

    @PutMapping
    @Transactional
    public void atualizarPacientes(@RequestBody @Valid AtualizarPacientes dados){
        var pacientes = repository.getReferenceById(dados.id());
        pacientes.atualizarInformacoes(dados);
    }

    @DeleteMapping
    @Transactional
    public void excluirPacientes(@PathVariable Long id){
        var pacientes = repository.getReferenceById(id);
        pacientes.excluirPacientes();

    }

}
