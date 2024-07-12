package med.voll.api.pacientes;

public record DadosListaPacientes(Long id,
                                  String nome,
                                  String email,
                                  String cpf) {

    public DadosListaPacientes(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
