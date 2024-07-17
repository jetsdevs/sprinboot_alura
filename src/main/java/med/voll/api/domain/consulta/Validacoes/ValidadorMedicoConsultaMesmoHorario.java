package med.voll.api.domain.consulta.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorMedicoConsultaMesmoHorario implements ValidadorAgendamentoConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoComConultaMesmoHorario = repository.existsByMedicoId(dados.idMedico());

        if (medicoComConultaMesmoHorario) {
            throw new ValidacaoException("Medico já possui consulta marcada nesse horario");
        }
    }
}
