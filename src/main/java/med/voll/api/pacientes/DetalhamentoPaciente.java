package med.voll.api.pacientes;

import med.voll.api.endereco.Endereco;

public record DetalhamentoPaciente(Long id,
                                   String nome,
                                   String email,
                                   String cpf,
                                   String telefone,
                                   Endereco endereco) {

    public DetalhamentoPaciente(Pacientes pacientes) {
        this(pacientes.getId(), pacientes.getNome(), pacientes.getEmail(),
                pacientes.getCpf(), pacientes.getTelefone(), pacientes.getEndereco());

    }

}
