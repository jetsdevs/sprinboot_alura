package med.voll.api.domain.pacientes;

public record DadosListaPacientes(Long id,
                                  String nome,
                                  String email,
                                  String cpf) {

    public DadosListaPacientes(Pacientes pacientes) {
        this(pacientes.getId(), pacientes.getNome(),
                pacientes.getEmail(), pacientes.getCpf());
    }
}
