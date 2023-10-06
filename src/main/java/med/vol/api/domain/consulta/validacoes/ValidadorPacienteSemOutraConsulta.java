package med.vol.api.domain.consulta.validacoes;

import med.vol.api.infra.exception.ValidacaoException;
import med.vol.api.domain.consulta.ConsultaRepository;
import med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsulta implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository repository;

    public void validar (DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.findByPacienteIdAndDataBetween
                (dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConsultaNoDia.getAtivo()) {
            if (!pacientePossuiOutraConsultaNoDia.getAtivo()) {
                throw new ValidacaoException("Paciente já possui outra consulta");
            }
        }
    }
}
