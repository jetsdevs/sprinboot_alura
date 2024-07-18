package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioCancelamento")
public class ValidadorHorarioCancelamento implements ValidadorCancelamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
      var consulta = repository.getReferenceById(dados.idConsulta());
      var agora = LocalDateTime.now();
      var diferencaHoras = Duration.between(agora, consulta.getData()).toHours();

      if (diferencaHoras < 24) {
          throw new ValidacaoException("Consulta somente poder cancelada com antecedencia minima de 24h!");
      }
    }
}
