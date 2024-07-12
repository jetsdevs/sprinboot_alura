package med.voll.api.pacientes;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;

public record AtualizarPacientes(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}