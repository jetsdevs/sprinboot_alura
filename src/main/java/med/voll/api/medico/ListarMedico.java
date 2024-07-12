package med.voll.api.medico;

import med.voll.api.endereco.Endereco;

public record ListarMedico(Long id,
                           String nome,
                           String email,
                           String crm,
                           Especialidade especialidade


) {

    public ListarMedico(Medico medico) {
        this(medico.getId(),
                medico.getNome(), medico.getEmail(), medico.getCrm(),
                medico.getEspecialidade());
    }
}
