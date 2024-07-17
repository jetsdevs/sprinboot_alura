package med.voll.api.domain.consulta.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void  validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsulta = repository.existsByPacientesIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacientePossuiOutraConsulta) {
            throw new ValidacaoException("Paciente já possui uma conslta agendada nesse dia");
        }
    }
}
