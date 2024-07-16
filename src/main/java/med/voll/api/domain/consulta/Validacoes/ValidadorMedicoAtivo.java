package med.voll.api.domain.consulta.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsultas {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){

        //escolha do medico opcional
        if (dados.idMedico() == null){
            return;
        }

        var medicoAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com medico inativo");
        }
    }
}
