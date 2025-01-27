package med.voll.api.domain.pacientes;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record AtualizarPacientes(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco,
        Boolean ativo) {

}
